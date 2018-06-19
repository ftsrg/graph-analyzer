library(ggplot2)
library(data.table)
library(plyr)
library(reshape2)
library(combinat)

source("R/transformation/ImportTSVs.R")
source("R/model_comparison/CalculatesHomogenityAndDistinctiveness.R")

figures.path <- "reports/"
default.theme <- theme(legend.position = "bottom")

########################### Reading #########################################
tsvs <- list.files("../models-metrics/", pattern = "-nd.tsv", full.names = T, recursive = T)
l <- lapply(tsvs, ImportsTSVToDataTable, cont = F)
tsv <- rbindlist(l)
tsv$file.name = basename(tsv$file.name)

figures.path <- "reports/"
#############################################################################################

########################### KS distance calculation #########################################
distribution.metrics <- c("MultiplexParticipationCoefficient", "PairwiseMultiplexity")

dm <- CalculatesKSDistance(tsv, distribution.metrics)
dm$mm1 <- substring(dm$first,  first = 0, last = 2)
dm$mm2 <- substring(dm$second, first = 0, last = 2)
#dm[dm$first == "R_20165.xmi-nd.tsv" & dm$second == "VW_1.xmi-nd.tsv", ]
#dm2[dm2$first == "R_20165.xmi-nd.tsv" & dm2$second == "VW_1.xmi-nd.tsv", ]

#############################################################################################

## Distinctiveness
distinctiveness <- CalculatesDistinctiveness(dm)
distinctiveness[distinctiveness$metric == "PairwiseMultiplexity", ]
distinctiveness[distinctiveness$metric == "MultiplexParticipationCoefficient", ]
