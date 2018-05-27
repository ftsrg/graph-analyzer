CalculatesKSDistance <- function(tsv, metrics.under.comparison) {
  distribut <- subset(tsv, Category %in% metrics.under.comparison)
  distribut <- dcast.data.table(distribut, file.name + Instance + Index ~ Category, value.var = "Value")
  files <- unique(distribut$file.name)
  distance.matrix <- as.data.frame(t(combn(files, m = 2)))
  colnames(distance.matrix) <- c("first", "second")
  distance.matrix$first  <- factor(distance.matrix$first,  levels = unique(distribut$file.name))
  distance.matrix$second <- factor(distance.matrix$second, levels = unique(distribut$file.name))
  distance.matrix = distance.matrix[substring(distance.matrix$first, 0, 2) == "R_" | substring(distance.matrix$second, 0, 2) == "R_", ]
  
  dm <- ddply(distance.matrix, .variables = c("first", "second"), .progress = "text" , .fun = function(pair){
    tmp <- subset(distribut, file.name == pair$first | file.name == pair$second)
    l <- lapply(seq(4, length(tmp)), function(col.index){
      x <- na.omit(as.data.frame(subset(tmp, file.name == pair$first, colnames(tmp)[col.index])))[, 1]
      y <- na.omit(as.data.frame(subset(tmp, file.name == pair$second, colnames(tmp)[col.index])))[, 1]
      if(length(x) < 2 | length(y) < 2) {
        data.frame(metric = colnames(tmp)[col.index], stat = NA)
      }
      ks <- ks.test(x = x, y = y)
      data.frame(metric = colnames(tmp)[col.index], stat = ks$statistic)
    })
    dm <- do.call(rbind, l)
  })
}

CalculatesDistinctiveness <- function(dm) {
  domainpairs <- dm[dm$mm1 != dm$mm2, ]
  s <- ddply(domainpairs, .variables = c("mm1", "mm2", "metric"), .progress = "text", .fun = function(df) {
    mean(df$stat, na.rm = T)
    #      /
    #      (df$stat[df$mm1 == unique(df$mm1) & df$metric == unique(df$metric)] +
    #       df$stat[df$mm1 == unique(df$mm2) & df$metric == unique(df$metric)])
  })
  s
}

CalculatesDistinctiveness2 <- function(dm) {
  distinctiveness <- data.frame(mm1 = NA, mm2 = NA, metric = NA, meanValue = NA)
  mms = unique(c(dm$mm1, dm$mm2))
  for(i in seq_along(mms)) {
    for(j in seq_along(mms)) {
      if(i != j & (substring(mms[i], 0, 2) == "R_" | substring(mms[j], 0, 2) == "R_")) {
        mms[c(i, j)]
        tmp <- dm[dm$mm1 %in% mms[c(i, j)] & 
                  dm$mm2 %in% mms[c(i, j)],
                 ]
        neighbor_ratio <- ddply(tmp, .variables = "metric", .progress = "text", .fun = function(df){
          files <- unique(c(as.character(df$first), as.character(df$second)))
          numbers <- lapply(files, function(file){
            s <- df[df$first == file | df$second == file, ]
            s <- s[with(s, order(stat)), ]
            print(summary(s))
            sum(s[1:2, "mm1"] == s[1:2, "mm2"])
          })
          mean(unlist(numbers))
        })
        neighbor_ratio$mm1 <- mms[i]
        neighbor_ratio$mm2 <- mms[j]
        neighbor_ratio
        # reorder columns
        neighbor_ratio <- neighbor_ratio[, c(3, 4, 1, 2)]
        colnames(neighbor_ratio)[4] <- "meanValue"
        distinctiveness <- rbind(distinctiveness, neighbor_ratio)
      }
    }
  }
  distinctiveness
}