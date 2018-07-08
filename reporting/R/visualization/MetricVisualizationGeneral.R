library(ggplot2)
library(plyr)
library(reshape2)
library(ggrepel)
library(data.table)
library(combinat)

source("R/transformation/ImportTSVs.R")

figures.path <- "reports/"
default.theme <- theme(legend.position = "bottom")

# put the tsv files to the tsv directory
tsvs <- list.files("tsv", pattern = ".tsv", full.names = T, recursive = T)
l <- lapply(tsvs, ImportsTSVToDataTable, cont = F)
tsv <- rbindlist(l)
tsv

source("R/metrics/MetricVisualization.R")
source("R/metrics/BasicInformationVisualization.R")
source("R/metrics/TypedEdgeVisualization.R")
source("R/metrics/GeneralNodeVisualization.R")
source("R/metrics/TypedNodeVisualization.R")
source("R/metrics/MetricVisualization.R")

################# Basic information #####################
basic.information <- subset(tsv, Category %in% c("Density",
                                                 "NumberOfNodes",
                                                 "NumberOfEdges"))
basic.information <- dcast.data.table(basic.information, file.name + GraphType ~ Category,
                                      value.var = "Value")
BasicInformationVisualization(basic.information, figures.path)

#########################################################

############## Typed edge metrics #################
edge.type <- subset(tsv, Category %in% c(#"NumberOfTypedEdges",
  "NodeTypeConnectivity",
  "NodeExclusiveTypeConnectivity",
  "EdgeTypeConnectivity"
))
edge.type <- dcast.data.table(data = edge.type, file.name + GraphType + Instance ~ Category,
                              value.var = "Value")

TypedEdgeVisualization(edge.type, basic.information, figures.path)

#########################################################

############# General node metrics ######################
node.type <- subset(tsv, Category %in% c(#"ClusteringCoefficientList",
#  "DegreeList",
  "MultiplexParticipationCoefficient",
  "TypedDegreeEntropy",
  "NodeActivity",
  "TypedClusteringCoefficient"
))
node.type <- dcast.data.table(data = node.type, file.name + GraphType + Index ~ Category, value.var = "Value")
GeneralNodeVisualization(node.type, figures.path)
#########################################################

#### Pairwise multiplexity ##############################
pairwise <- subset(tsv, Category %in% "PairwiseMultiplexity")
setnames(pairwise, "Value", "PairwiseMultiplexity")
PlotsECDFByFileName       (pairwise, paste0(figures.path, "PairwiseMultiplexity_ecdf_faceted.pdf"), x = "PairwiseMultiplexity", col = "GraphType", title = 'PairwiseMultiplexity')
PlotsECDFByFileNameOneSide(pairwise, paste0(figures.path, "PairwiseMultiplexity_ecdf_oneside.pdf"), x = "PairwiseMultiplexity", col = "GraphType", title = 'PairwiseMultiplexity')

#### Edge overlap ##############################
edgeoverlap <- subset(tsv, Category %in% "EdgeOverlap")
setnames(edgeoverlap, "Value", "EdgeOverlap")
PlotsECDFByFileName       (edgeoverlap, paste0(figures.path, "EdgeOverlap_ecdf_faceted.pdf"), x = "EdgeOverlap", col = "GraphType", title = 'EdgeOverlap')
PlotsECDFByFileNameOneSide(edgeoverlap, paste0(figures.path, "EdgeOverlap_ecdf_oneside.pdf"), x = "EdgeOverlap", col = "GraphType", title = 'EdgeOverlap')
