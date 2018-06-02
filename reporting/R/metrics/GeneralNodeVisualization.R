GeneralNodeVisualization <- function(node.type, figures.path){
  lapply(seq(4, length(node.type)), function(col.ind){
    # PlotsHistogramByFileName(dt = node.type,
    #                          plot.file.name = paste0(figures.path, colnames(node.type)[col.ind], ".pdf"),
    #                          x = colnames(node.type)[col.ind], fill = "ModelType",
    #                          facetwrap = "file.name", scalex = F)
    PlotsECDFByFileName(dt = node.type,
                        plot.file.name = paste0(figures.path, colnames(node.type)[col.ind], "_ecdf_faceted.pdf"),
                        x = colnames(node.type)[col.ind],
                        col = "ModelType",
                        facetwrap = "ModelType",
                        scalex = F,
                        title = colnames(node.type)[col.ind])
    PlotsECDFByFileNameOneSide(dt = node.type,
                               plot.file.name = paste0(figures.path, colnames(node.type)[col.ind], "_ecdf_oneside.pdf"),
                               x = colnames(node.type)[col.ind],
                               col = "ModelType",
                               scalex = F,
                               title = colnames(node.type)[col.ind])
    })
  
  # PlotsBasicScatterplot(dt = node.type,
  #                       plot.file.name = paste0(figures.path, "DegreeListVsNodeActivityList.pdf"),
  #                       x = "DegreeList",
  #                       y = "NodeActivityList",
  #                       col = "ModelType", facetwrap = "file.name",
  #                       scalex = T, scaley = T)
  # 
  # PlotsBasicScatterplot(dt = node.type,
  #                       plot.file.name = paste0(figures.path, "NodeActivityListVsMultiplexParticipationCoefficient.pdf"),
  #                       x = "NodeActivityList",
  #                       y = "MultiplexParticipationCoefficient",
  #                       col = "ModelType", facetwrap = "file.name",
  #                       scalex = T, scaley = T)
  # 
  # PlotsBasicScatterplot(dt = node.type,
  #                       plot.file.name = paste0(figures.path, "ClusteringCoefficientListVsDimensionalClusteringCoefficientDef1.pdf"),
  #                       x = "ClusteringCoefficientList",
  #                       y = "DimensionalClusteringCoefficientDef1",
  #                       col = "ModelType", facetwrap = "file.name")
  # 
  # PlotsBasicScatterplot(dt = node.type,
  #                       plot.file.name = paste0(figures.path, "ClusteringCoefficientListVsDimensionalClusteringCoefficientDef2.pdf"),
  #                       x = "ClusteringCoefficientList",
  #                       y = "DimensionalClusteringCoefficientDef2",
  #                       col = "ModelType", facetwrap = "file.name")
  # 
  # 
  # PlotsBasicScatterplot(dt = node.type,
  #                       plot.file.name = paste0(figures.path, "DimensionalClusteringCoefficientDefs.pdf"),
  #                       x = "DimensionalClusteringCoefficientDef1",
  #                       y = "DimensionalClusteringCoefficientDef2",
  #                       col = "ModelType", facetwrap = "file.name")
}