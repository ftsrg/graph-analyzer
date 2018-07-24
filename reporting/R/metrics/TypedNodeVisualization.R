TypedNodeVisualization <- function(type.node, dt = T) {
  type.node <- dcast.data.table(data = type.node, file.name + GraphType + Index  + Instance ~ Category,
                                 value.var = "Value")
  if(dt) {
    type.type <- subset(type.node, TypedClusteringCoefficientList > 0)
    PlotsHistogramByFileName(dt = type.type,
                             plot.file.name = paste0(figures.path, "TypedClusteringCoef.pdf"),
                             x = "TypedClusteringCoefficientList",
                             fill = "GraphType",
                             facetwrap = "file.name")
  }
  
  type.list <- subset(type.node, TypedDegreeList > 0)
  PlotsBoxplot(type.list, paste0(figures.path, "TypedDegree.pdf"),
               x = "Instance", y = "TypedDegreeList", facetwrap = "file.name", scaley = T)
}
