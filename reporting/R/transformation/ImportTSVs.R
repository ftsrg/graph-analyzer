ImportsTSVToDataTable <- function(file.name, cont = T){
  gc()
  print(file.name)
  tsv <- fread(file.name, sep = "\t")
  
  ## removing every useless metric
  distribution.metrics <- c( "ClusteringCoefficientList",
                             "DegreeList" , "DimensionActivity" ,
                             "DimensionalClusteringCoefficientDef1" ,
                             "DimensionalClusteringCoefficientDef2" ,
                             "DimensionalClusteringCoefficientDef3" ,
                             "EdgeDimensionConnectivity" ,
                             "MultiplexParticipationCoefficient",
                             "NodeActivityList" ,
                             "NodeDimensionConnectivity" ,
                             "NodeExclusiveDimensionConnectivity",
                             "NumberOfTypedEdges",
                             "PairwiseMultiplexity")
  tsv <- subset(tsv, Category %in% c(distribution.metrics, "Density", "NumberOfNodes",
                                     "NumberOfEdges"))
  
  #browser()
  tsv[, file.name := file.name]
  tsv[, file.name := gsub(pattern = "tsv/", "", tsv$file.name)]
  tsv[, file.name := gsub(pattern = "\\.tsv", "", tsv$file.name)]
  
  tsv[, ModelType := file.name]
  tsv[, Scope := "nd"]  
  tsv[, Instance := gsub("-", "_", tsv$Instance)]
  
  ## normalization
  raw <- subset(tsv, !(Category %in% c("DegreeList", "DimensionActivity", "NodeActivityList", "DimensionalDegreeList")))
  
  basic.information <- subset(tsv, Category %in% c("Density", "NumberOfNodes",
                                                   "NumberOfEdges"))
  basic.information <- dcast.data.table(basic.information, file.name ~ Category,
                                        value.var = "Value")
  ## how big is the ratio?
  degreelist <- subset(tsv, Category %in% c("DegreeList"))
  degreelist <- merge(degreelist,
                      basic.information[, c("file.name", "NumberOfEdges"), with = F],
                      by = c("file.name"))
  
  degreelist$Value <- degreelist$Value / degreelist$NumberOfEdges
  degreelist[, NumberOfEdges := NULL]
  raw <- merge(raw, degreelist, by = colnames(raw), all = T)
  
  ## dimensional degree list
  dimen.degreelist <- subset(tsv, Category %in% c("DimensionalDegreeList"))
  dimen.degreelist <- merge(dimen.degreelist,
                      basic.information[, c("file.name", "NumberOfNodes"), with = F],
                      by = c("file.name"))
  dimen.degreelist[, Value := Value / NumberOfNodes]
  dimen.degreelist[, NumberOfNodes := NULL]
  raw <- merge(raw, dimen.degreelist, by = colnames(raw), all = T)
  
  ## dimension activity
  dimensionactivity <- subset(tsv, Category %in% c("DimensionActivity"))
  ## ratio of nodes instead of their absolute values
  dimensionactivity <- merge(dimensionactivity,
                             basic.information[, c("file.name", "NumberOfNodes"), with = F],
                             by = c("file.name"))
  dimensionactivity$Value <- dimensionactivity$Value / dimensionactivity$NumberOfNodes
  dimensionactivity[, NumberOfNodes := NULL]
  raw <- merge(raw, dimensionactivity, by = colnames(raw), all = T)
  
  ## node activity
  nodeactivity <- subset(tsv, Category %in% c("NodeActivityList"))
  ## how many dimensions do we have at all?
  number.of.typed.edges <- subset(tsv, Category %in% "NumberOfTypedEdges")
  number.of.dimensions <- number.of.typed.edges[ ,list(numberofDimensions = length(Instance)), by= c("file.name")]
  nodeactivity <- merge(nodeactivity, number.of.dimensions, by = c("file.name"))
  nodeactivity$Value <- nodeactivity$Value / nodeactivity$numberofDimensions
  nodeactivity[,numberofDimensions := NULL ]
  raw <- merge(raw, nodeactivity, by = colnames(raw), all = T)
  raw
}
