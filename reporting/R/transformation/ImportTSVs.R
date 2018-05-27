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

  tsv[, file.name := gsub(pattern = "../models/", "", tsv$file.name)]

  file.name <- gsub("/", "_", unique(tsv$file.name), fixed = T)
  category <- strsplit(file.name, split = "_", fixed = T)[[1]][2]
  tsv[, category := category]
  
  if(grepl("VB", x = file.name)){tsv[, ModelType := "VIATRA Solver (base)"]} else
  if(grepl("VM", x = file.name)){tsv[, ModelType := "VIATRA Solver (MM)"]} else
  if(grepl("VW", x = file.name)){tsv[, ModelType := "VIATRA Solver (WF)"]} else
  if(grepl("AB", x = file.name)){tsv[, ModelType := "Alloy (base)"]} else
  if(grepl("AM", x = file.name)){tsv[, ModelType := "Alloy (MM)"]} else
  if(grepl("AW", x = file.name)){tsv[, ModelType := "Alloy (WF)"]} else
  if(grepl("R", x = file.name)) {tsv[, ModelType := "Real"]} else
  {error('unknown model type')}
  
  tsv[, Scope := "nd"]  
  tsv[, Instance := gsub("-", "_", tsv$Instance)]
  
  ## inserting the treeRatio metric
  # edge.type <- subset(tsv, Category %in% c("NumberOfTypedEdges", "DimensionActivity"))
  # edge.type <- dcast.data.table(data = edge.type, file.name + category + Instance + Iscontainment + Index + ModelType + Scope ~ Category,
  #                               value.var = "Value")
  # #edge.type$NodeToEdgeRatio <- edge.type$DimensionActivity / edge.type$NumberOfTypedEdges
  # edge.type[, NumberOfTypedEdges := NULL]
  # edge.type[, DimensionActivity := NULL]
  # setnames(edge.type, "NodeToEdgeRatio", "Value")
  # edge.type$Category <- "NodeToEdgeRatioByType"
  # tsv <- merge(tsv, edge.type, by = colnames(tsv), all = T)
  
  ## normalization
  raw <- subset(tsv, !(Category %in% c("DegreeList", "DimensionActivity", "NodeActivityList", "DimensionalDegreeList")))
  
  basic.information <- subset(tsv, Category %in% c("Density", "NumberOfNodes",
                                                   "NumberOfEdges"))
  basic.information <- dcast.data.table(basic.information, file.name + category ~ Category,
                                        value.var = "Value")
  ## how big is the ratio?
  degreelist <- subset(tsv, Category %in% c("DegreeList"))
  degreelist <- merge(degreelist,
                      basic.information[, c("file.name", "category", "NumberOfEdges"), with = F],
                      by = c("file.name", "category"))
  
  degreelist$Value <- degreelist$Value / degreelist$NumberOfEdges
  degreelist[, NumberOfEdges := NULL]
  raw <- merge(raw, degreelist, by = colnames(raw), all = T)
  
  ## dimensional degree list
  dimen.degreelist <- subset(tsv, Category %in% c("DimensionalDegreeList"))
  dimen.degreelist <- merge(dimen.degreelist,
                      basic.information[, c("file.name", "category", "NumberOfNodes"), with = F],
                      by = c("file.name", "category"))
  dimen.degreelist[, Value := Value / NumberOfNodes]
  dimen.degreelist[, NumberOfNodes := NULL]
  raw <- merge(raw, dimen.degreelist, by = colnames(raw), all = T)
  
  ## dimension activity
  dimensionactivity <- subset(tsv, Category %in% c("DimensionActivity"))
  ## ratio of nodes instead of their absolute values
  dimensionactivity <- merge(dimensionactivity,
                             basic.information[, c("file.name", "category", "NumberOfNodes"), with = F],
                             by = c("file.name", "category"))
  dimensionactivity$Value <- dimensionactivity$Value / dimensionactivity$NumberOfNodes
  dimensionactivity[, NumberOfNodes := NULL]
  raw <- merge(raw, dimensionactivity, by = colnames(raw), all = T)
  
  ## node activity
  nodeactivity <- subset(tsv, Category %in% c("NodeActivityList"))
  ## how many dimensions do we have at all?
  number.of.typed.edges <- subset(tsv, Category %in% "NumberOfTypedEdges")
  number.of.dimensions <- number.of.typed.edges[ ,list(numberofDimensions = length(Instance)), by= c("file.name", "category")]
  nodeactivity <- merge(nodeactivity, number.of.dimensions, by = c("file.name", "category"))
  nodeactivity$Value <- nodeactivity$Value / nodeactivity$numberofDimensions
  nodeactivity[,numberofDimensions := NULL ]
  raw <- merge(raw, nodeactivity, by = colnames(raw), all = T)
  raw[, category := ModelType]
  raw
}

