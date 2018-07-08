GeneralNodeVisualization <- function(node.type, figures.path){
  lapply(seq(4, length(node.type)), function(col.ind){
    # PlotsHistogramByFileName(dt = node.type,
    #                          plot.file.name = paste0(figures.path, colnames(node.type)[col.ind], ".pdf"),
    #                          x = colnames(node.type)[col.ind], fill = "GraphType",
    #                          facetwrap = "file.name", scalex = F)
    PlotsECDFByFileName(dt = node.type,
                        plot.file.name = paste0(figures.path, colnames(node.type)[col.ind], "_ecdf_faceted.pdf"),
                        x = colnames(node.type)[col.ind],
                        col = "GraphType",
                        facetwrap = "GraphType",
                        scalex = F,
                        title = colnames(node.type)[col.ind])
    PlotsECDFByFileNameOneSide(dt = node.type,
                               plot.file.name = paste0(figures.path, colnames(node.type)[col.ind], "_ecdf_oneside.pdf"),
                               x = colnames(node.type)[col.ind],
                               col = "GraphType",
                               scalex = F,
                               title = colnames(node.type)[col.ind])
    })
  
  # PlotsBasicScatterplot(dt = node.type,
  #                       plot.file.name = paste0(figures.path, "DegreeListVsNodeActivityList.pdf"),
  #                       x = "DegreeList",
  #                       y = "NodeActivityList",
  #                       col = "GraphType", facetwrap = "file.name",
  #                       scalex = T, scaley = T)
  # 
  # PlotsBasicScatterplot(dt = node.type,
  #                       plot.file.name = paste0(figures.path, "NodeActivityListVsMultiplexParticipationCoefficient.pdf"),
  #                       x = "NodeActivityList",
  #                       y = "MultiplexParticipationCoefficient",
  #                       col = "GraphType", facetwrap = "file.name",
  #                       scalex = T, scaley = T)
  # 
  # PlotsBasicScatterplot(dt = node.type,
  #                       plot.file.name = paste0(figures.path, "ClusteringCoefficientListVsTypedClusteringCoefficientDef1.pdf"),
  #                       x = "ClusteringCoefficientList",
  #                       y = "TypedClusteringCoefficientDef1",
  #                       col = "GraphType", facetwrap = "file.name")
  # 
  # PlotsBasicScatterplot(dt = node.type,
  #                       plot.file.name = paste0(figures.path, "ClusteringCoefficientListVsTypedClusteringCoefficientDef2.pdf"),
  #                       x = "ClusteringCoefficientList",
  #                       y = "TypedClusteringCoefficientDef2",
  #                       col = "GraphType", facetwrap = "file.name")
  # 
  # 
  # PlotsBasicScatterplot(dt = node.type,
  #                       plot.file.name = paste0(figures.path, "TypedClusteringCoefficientDefs.pdf"),
  #                       x = "TypedClusteringCoefficientDef1",
  #                       y = "TypedClusteringCoefficientDef2",
  #                       col = "GraphType", facetwrap = "file.name")
}