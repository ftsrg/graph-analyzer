BasicInformationVisualization <- function(basic.information, figures.path){
  basic.information$RatioOfEdges <- basic.information$NumberOfEdges /basic.information$NumberOfNodes
  PlotsScatterplotByFileName(basic.information, paste0(figures.path, "nodes_vs_edges.pdf"),
                             x = "NumberOfNodes", y = "NumberOfEdges", scalex = F, scaley = F)
  PlotsScatterplotByFileName(basic.information, paste0(figures.path, "edges_vertex_ratio.pdf"),
                                                     x = "NumberOfNodes", 
                             y= "RatioOfEdges", scalex = F, scaley = F)
  
  PlotsScatterplotByFileName(basic.information, paste0(figures.path, "nodes_vs_density.pdf"),
                             x = "NumberOfNodes", y = "Density", scaley = F)
}

BasicInformationVisualizationContainment <- function(basic.information, instances, figures.path){
  number.of.cont.edges <- instances[, list(sumOfEdges = sum(Value)),
                                    by = c("file.name", "ModelType", "Iscontainment")]
  basic.information <- merge(basic.information, number.of.cont.edges, by = c("file.name", "ModelType"))
  tmp <- dcast(basic.information, file.name + ModelType + NumberOfNodes ~ Iscontainment)
  tmp$r <- tmp$true / (tmp$true + tmp$false)
  
  PlotsScatterplotByFileName(tmp, plot.file.name = paste0(figures.path, "ratio_of_containment_edges.pdf"),
                             x = "NumberOfNodes", y = "r", col = "ModelType", scaley = F)
  
  PlotsScatterplotByFileName(basic.information, plot.file.name = paste0(figures.path, "SumOfEdges.pdf"),
                             x = "NumberOfNodes", y = "sumOfEdges", col = "ModelType", shape = "Iscontainment")
}