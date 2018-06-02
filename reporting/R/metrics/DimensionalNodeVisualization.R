DimensionalNodeVisualization <- function(dimen.node, dt = T){
  dimen.node <- dcast.data.table(data = dimen.node, file.name + ModelType + Index  + Instance ~ Category,
                                 value.var = "Value")
  if(dt) {
    dimen.type <- subset(dimen.node, DimensionalTypedClusteringCoefficientList > 0)
    PlotsHistogramByFileName(dt = dimen.type,
                             plot.file.name = paste0(figures.path, "DimensionalTypedClusteringCoef.pdf"),
                             x = "DimensionalTypedClusteringCoefficientList",
                             fill = "ModelType",
                             facetwrap = "file.name")
  }
  
  dimen.list <- subset(dimen.node, DimensionalDegreeList > 0)
  PlotsBoxplot(dimen.list, paste0(figures.path, "DimensionalDegree.pdf"),
               x = "Instance", y = "DimensionalDegreeList", facetwrap = "file.name", scaley = T)
  
}

DimensionalNodeVisualizationContainment <- function(dimen.node){
  dimen.node <- dcast.data.table(data = dimen.node, file.name + ModelType + Index  + Instance + Iscontainment ~ Category,
                                 value.var = "Value")
  dimen.type <- subset(dimen.node, DimensionalTypedClusteringCoefficientList > 0)
  PlotsHistogramByFileName(dt = dimen.type,
                           plot.file.name = paste0(figures.path, "DimensionalTypedClusteringCoef_containment.pdf"),
                           x = "DimensionalTypedClusteringCoefficientList",
                           fill = "ModelType",
                           facetwrap = "file.name")
  dimen.list <- subset(dimen.node, DimensionalDegreeList > 0)
  PlotsBoxplotGrid(dimen.list, paste0(figures.path, "DimensionalDegree_containment.pdf"),
               x = "Instance", y = "DimensionalDegreeList", facetgrid = "file.name ~ Iscontainment", scaley = T)
  
}