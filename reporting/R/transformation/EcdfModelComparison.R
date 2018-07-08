library(ggplot2)
library(data.table)
library(plyr)
library(reshape2)
library(combinat)

#tsv <- fread("data/metrics_full.csv")
figures.path <- "reports/figures/paper/"

distribution.metrics <- c( "ClusteringCoefficientList",
                           "DegreeList" , "TypeActivity" ,
                           "TypedClusteringCoefficientDef1" ,
                           "TypedClusteringCoefficientDef2" ,
                           "TypedClusteringCoefficientDef3" ,
                          "EdgeTypeConnectivity" ,
                          "MultiplexParticipationCoefficient",
                          "NodeActivityList" ,
                          "NodeTypeConnectivity" ,
                          "NodeExclusiveTypeConnectivity",
                          "NumberOfTypedEdges",
                          "PairwiseMultiplexity")

distribut <- subset(tsv, Category %in% distribution.metrics)
distribut <- dcast.data.table(distribut, file.name + GraphType + Iscontainment + Instance + Index ~ Category, value.var = "Value")
files <- unique(distribut$file.name)
#base.of.com <- files[c(1:5, 41,44, 48, 49:51, 54:56,62:63, 70:73)]
#tr <- files[c(1, 41, 49, 54, 62, 70)]
base.of.com <- files
tr <- files
distance.matrix <- as.data.frame(t(combn(base.of.com, m = 2)))
colnames(distance.matrix) <- c("first", "second")
distance.matrix$first <- factor(distance.matrix$first, levels = unique(distribut$file.name))
distance.matrix$second <- factor(distance.matrix$second, levels = unique(distribut$file.name))

dm <- ddply(distance.matrix, .variables = c("first", "second"), .progress = "text" , .fun = function(pair){
  print(pair)
  tmp <- subset(distribut, file.name == pair$first | file.name == pair$second)
  l <- lapply(seq(6, length(tmp)), function(col.index){
    print(colnames(tmp)[col.index])
    x <- na.omit(as.data.frame(subset(tmp, file.name == pair$first, colnames(tmp)[col.index])))[, 1]
    y <- na.omit(as.data.frame(subset(tmp, file.name == pair$second, colnames(tmp)[col.index])))[, 1]
    if(length(x) < 2 | length(y) < 2) {
      data.frame(metric = colnames(tmp)[col.index], stat = NA)
    }
    ks <- ks.test(x = x,
                  y = y)
    data.frame(metric = colnames(tmp)[col.index], stat = ks$statistic)
  })
  dm <- do.call(rbind, l)
})















ecdfComparison <- function(tmp, ind, plot.file.name){
  files <- unique(tmp$file.name)
  distances <- matrix(data = 0, nrow = length(files), ncol = length(files))
  
  lapply(seq_along(files), function(file1){
    print(file1)
    file1.dist <- tmp[tmp$file.name == files[[file1]], ind]
    ecdf1 <- ecdf(file1.dist)
    min1 <- min(file1.dist)
    max1 <- max(file1.dist)
    lapply(seq_along(files), function(file2){
      print(file2)
      file2.dist <- tmp[tmp$file.name == files[[file2]], ind]
      ecdf2 <- ecdf(file2.dist)
      min2 <- min(file2.dist)
      max2 <- max(file2.dist)
      distance <- sum(sqrt(
        (ecdf1(seq(from = min(min1, min2), to = max(max1, max2), length.out = 1000)) - 
           ecdf2(seq(from = min(min1, min2), to = max(max1, max2), length.out = 1000)))^2
      ))
      distances[file1, file2] <<- unlist(distance)  
      T
    })
    T
  })  
  df <- as.data.frame(distances)
  colnames(df)  <- files
  df$files <- files
  df$metric <- colnames(tmp)[ind]
  df
#   require(reshape2)
#   d <- melt(df, id.vars = "files")
#   base <- ggplot(d)
#   base <- base + geom_tile(aes(x = files, y = variable, fill = value))
#   pdf(paste0(figures.path, plot.file.name, "_raw_coeff.pdf"))
#   print(base)
#   dev.off()
}

distribut <- subset(tsv, Category %in% distribution.metrics)
distribut <- dcast.data.table(distribut, file.name + GraphType + Iscontainment + Instance + Index ~ Category, value.var = "Value")

l <- lapply(seq(6, length(distribut)), function(col.index){
  print(colnames(distribut)[col.index])
  tmp <- distribut[,
                   c("file.name", "GraphType", "Iscontainment", "Instance", "Index", colnames(distribut)[col.index]),
                   with = F]
  tmp <- as.data.frame(tmp)
  tmp <- tmp[!is.na(tmp[, 6]), ]
  df <- ecdfComparison(tmp, 6, plot.file.name = colnames(distribut)[col.index])
  df
})

df <- do.call(rbind, l)
s <- ddply(df, .variables = "metric", .fun = function(ddf){
  melted <- melt(ddf, id.vars = c("metric", "files"))
  melted <- melted[melted$files != melted$variable, ]
  melted$mm1 <- substring(text = melted$files, first = 1, last = 4)
  melted$mm2 <- substring(text = melted$variable, first = 1, last = 4)
  ddply(melted, .variables = c("mm1", "mm2"), summarize, minvalue = min(value), maxvalue = max(value))
})
s <- melt(s, id.vars = c("metric", "mm1", "mm2"))

s <- dcast(data = s, metric ~ mm1 + mm2 + variable)
s <- melt(s, id.vars = "metric")
base <- ggplot(s)
base + geom_tile(aes(x = variable, y = metric, fill = value)) +
  geom_text(aes(x = variable, y = metric, label = round(value)), color = 'white') +
  coord_flip()
