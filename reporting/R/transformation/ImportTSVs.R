ImportsTSVToDataTable <- function(file.name, cont = T){
  gc()
  print(file.name)
  tsv <- fread(file.name, sep = "\t")
  
  ## removing every useless metric
  # distribution.metrics <- c( "ClusteringCoefficientList",
  #                            "DegreeList" , 
  #                            "TypeActivity" ,
  #                            "TypedClusteringCoefficientDef1" ,
  #                            "TypedClusteringCoefficientDef2" ,
  #                            "TypedClusteringCoefficientDef3" ,
  #                            "TypedDegreeEntropy" ,
  #                            "EdgeOverlap" , 
  #                            "EdgeTypeCdonnectivity" ,
  #                            "NodeTypedConnectivity" ,
  #                            "MultiplexParticipationCoefficient",
  #                            "NodeActivityList" ,
  #                            "NumberOfTypedEdges",
  #                            "PairwiseMultiplexity"
  #                          )
  #tsv <- subset(tsv, Category %in% c(distribution.metrics, "Density", "NumberOfNodes",
  #                                   "NumberOfEdges"))
  
  #browser()
  tsv[, file.name := file.name]
  tsv[, file.name := gsub(pattern = "tsv/", "", tsv$file.name)]
  tsv[, file.name := gsub(pattern = "\\.tsv", "", tsv$file.name)]
  
  tsv[, GraphType := file.name]
  tsv[, Scope := "nd"]  
  tsv[, Instance := gsub("-", "_", tsv$Instance)]
  
  ## normalization
  raw <- subset(tsv, !(Category %in% c("DegreeList", "TypeActivity", "NodeActivityList", "TypedDegreeList")))
  
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
  
  ## typeal degree list
  type.degreelist <- subset(tsv, Category %in% c("TypedDegreeList"))
  type.degreelist <- merge(type.degreelist,
                      basic.information[, c("file.name", "NumberOfNodes"), with = F],
                      by = c("file.name"))
  type.degreelist[, Value := Value / NumberOfNodes]
  type.degreelist[, NumberOfNodes := NULL]
  raw <- merge(raw, type.degreelist, by = colnames(raw), all = T)
  
  ## type activity
  typeactivity <- subset(tsv, Category %in% c("TypeActivity"))
  ## ratio of nodes instead of their absolute values
  typeactivity <- merge(typeactivity,
                             basic.information[, c("file.name", "NumberOfNodes"), with = F],
                             by = c("file.name"))
  typeactivity$Value <- typeactivity$Value / typeactivity$NumberOfNodes
  typeactivity[, NumberOfNodes := NULL]
  raw <- merge(raw, typeactivity, by = colnames(raw), all = T)
  
  ## node activity
  nodeactivity <- subset(tsv, Category %in% c("NodeActivityList"))
  ## how many types do we have at all?
  number.of.typed.edges <- subset(tsv, Category %in% "NumberOfTypedEdges")
  number.of.types <- number.of.typed.edges[ ,list(numberofTypes = length(Instance)), by= c("file.name")]
  nodeactivity <- merge(nodeactivity, number.of.types, by = c("file.name"))
  nodeactivity$Value <- nodeactivity$Value / nodeactivity$numberofTypes
  nodeactivity[,numberofTypes := NULL ]
  raw <- merge(raw, nodeactivity, by = colnames(raw), all = T)
  raw
}
