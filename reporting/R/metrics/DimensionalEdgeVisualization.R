DimensionalEdgeVisualization <- function(edge.type, basic.information, figures.path){
  ## how many edge types do they have?
  number.of.dims <- edge.type[,  list(NumberOfDimensions=length(Instance)), by = c("file.name", "ModelType")]
  number.of.dims <- merge(basic.information, number.of.dims, by = c("file.name", "ModelType"))
  PlotsScatterplotByFileName(number.of.dims, paste0(figures.path, "numberofdimensions.pdf"),
                     x = "NumberOfEdges", y = "NumberOfDimensions", col = "ModelType", label = "file.name")
  
  lapply(seq(4, length(edge.type)), function(col.ind){
    # PlotsHistogramByFileName(dt = edge.type,
    #                          plot.file.name = paste0(figures.path, colnames(edge.type)[col.ind], ".pdf"),
    #                          x = colnames(edge.type)[col.ind],
    #                          fill = "ModelType",
    #                          facetwrap = "file.name",
    #                          scalex = T)
    PlotsECDFByFileName(dt = edge.type,
                             plot.file.name = paste0(figures.path, colnames(edge.type)[col.ind], "_ecdf_faceted.pdf"),
                             x = colnames(edge.type)[col.ind],
                             col = "ModelType",
                             facetwrap = "ModelType",
                             scalex = F,
                             title = colnames(edge.type)[col.ind])
    PlotsECDFByFileNameOneSide(dt = edge.type,
                        plot.file.name = paste0(figures.path, colnames(edge.type)[col.ind], "_ecdf_oneside.pdf"),
                        x = colnames(edge.type)[col.ind],
                        col = "ModelType",
                        scalex = F,
                        title = colnames(edge.type)[col.ind])
    })
  
  #edge.type$ratio <- edge.type$DimensionActivity / edge.type$NumberOfTypedEdges
#  PlotsBasicScatterplot(edge.type, paste0(figures.path, "DimensionActivityratio.pdf"), x = "EdgeDimensionConnectivity", y = "NodeToEdgeRatioByType", col = "ModelType", facetwrap = "file.name", scalex = F)
}
