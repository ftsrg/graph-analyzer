library(ggplot2)
library(plyr)
library(reshape2)
library(ggrepel)
library(data.table)
library(combinat)

source("R/transformation/ImportTSVs.R")

figures.path <- "reports/"
default.theme <- theme_bw() + theme(legend.position = "bottom")

tsvs <- list.files("../models-metrics/", pattern = "-nd.tsv", full.names = T, recursive = T)

# uncomment this for testing
# tsvs <- c(
#   "../models-metrics/VW_1.xmi-nd.tsv", 
#   "../models-metrics/VW_2.xmi-nd.tsv", 
#   "../models-metrics/VW_1.xmi-nd.tsv", 
#   "../models-metrics/R_201631.xmi-nd.tsv", 
#   "../models-metrics/R_2017156.xmi-nd.tsv",
#   "../models-metrics/R_2017254.xmi-nd.tsv"
#   )

l <- lapply(tsvs, ImportsTSVToDataTable, cont = F)
tsv <- rbindlist(l)
tsv$file.name = gsub("\\.\\./models-metrics/", "", tsv$file.name)
#tsv[, file.name := as.numeric(as.factor(file.name))]
#tsv[, file.name := paste0("File ", file.name)]
#tsv[, file.name := factor(file.name, levels = paste0("File ", seq_len(length(unique(tsv$file.name)))))]

source("R/metrics/MetricVisualization.R")
source("R/metrics/BasicInformationVisualization.R")
source("R/metrics/DimensionalEdgeVisualization.R")
source("R/metrics/GeneralNodeVisualization.R")
source("R/metrics/DimensionalNodeVisualization.R")
source("R/metrics/MetricVisualization.R")

################# Basic information #####################
basic.information <- subset(tsv, Category %in% c("Density",
                                                 "NumberOfNodes",
                                                 "NumberOfEdges"))
basic.information <- dcast.data.table(basic.information, file.name + category ~ Category,
                                      value.var = "Value")
BasicInformationVisualization(basic.information, figures.path)

#########################################################

############## Dimensional edge metrics #################
edge.type <- subset(tsv, Category %in% c(#"NumberOfTypedEdges",
  "NodeDimensionConnectivity",
  "NodeExclusiveDimensionConnectivity",
  "EdgeDimensionConnectivity"
))
edge.type <- dcast.data.table(data = edge.type, file.name + category + Instance ~ Category,
                              value.var = "Value")

DimensionalEdgeVisualization(edge.type, basic.information, figures.path)

#########################################################

############# General node metrics ######################
node.type <- subset(tsv, Category %in% c(#"ClusteringCoefficientList",
  "DegreeList",
  "MultiplexParticipationCoefficient"
))
node.type <- dcast.data.table(data = node.type, file.name + category + Index ~ Category, value.var = "Value")
GeneralNodeVisualization(node.type, figures.path)
#########################################################

#### Pairwise multiplexity ##############################
pairwise <- subset(tsv, Category %in% "PairwiseMultiplexity")
setnames(pairwise, "Value", "PairwiseMultiplexity")

PlotsECDFByFileName(pairwise, paste0(figures.path, "PairwiseMultiplexity_ecdf_faceted.pdf"), x = "PairwiseMultiplexity", col = "category", title = 'PairwiseMultiplexity')
#PlotsECDFByFileNameOneSide(pairwise, paste0(figures.path, "PairwiseMultiplexity_ecdf_oneside.pdf"), x = "PairwiseMultiplexity", col = "category", title = 'PairwiseMultiplexity')
