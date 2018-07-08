TypedNodeVisualization <- function(type.node, dt = T){
  type.node <- dcast.data.table(data = type.node, file.name + GraphType + Index  + Instance ~ Category,
                                 value.var = "Value")
  if(dt) {
    type.type <- subset(type.node, TypedTypedClusteringCoefficientList > 0)
    PlotsHistogramByFileName(dt = type.type,
                             plot.file.name = paste0(figures.path, "TypedTypedClusteringCoef.pdf"),
                             x = "TypedTypedClusteringCoefficientList",
                             fill = "GraphType",
                             facetwrap = "file.name")
  }
  
  type.list <- subset(type.node, TypedDegreeList > 0)
  PlotsBoxplot(type.list, paste0(figures.path, "TypedDegree.pdf"),
               x = "Instance", y = "TypedDegreeList", facetwrap = "file.name", scaley = T)
  
}

TypedNodeVisualizationContainment <- function(type.node){
  type.node <- dcast.data.table(data = type.node, file.name + GraphType + Index  + Instance + Iscontainment ~ Category,
                                 value.var = "Value")
  type.type <- subset(type.node, TypedTypedClusteringCoefficientList > 0)
  PlotsHistogramByFileName(dt = type.type,
                           plot.file.name = paste0(figures.path, "TypedTypedClusteringCoef_containment.pdf"),
                           x = "TypedTypedClusteringCoefficientList",
                           fill = "GraphType",
                           facetwrap = "file.name")
  type.list <- subset(type.node, TypedDegreeList > 0)
  PlotsBoxplotGrid(type.list, paste0(figures.path, "TypedDegree_containment.pdf"),
               x = "Instance", y = "TypedDegreeList", facetgrid = "file.name ~ Iscontainment", scaley = T)
  
}