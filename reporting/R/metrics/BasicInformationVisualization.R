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
