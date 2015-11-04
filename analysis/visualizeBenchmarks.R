source("util.R")
usePackage("plyr")
usePackage("ggplot2")

visualize <- function(data, xAxis, yAxis, legend, minValue, maxValue) {
    plot <- ggplot(data, aes_string(x = xAxis, y = yAxis)) +
    # geom_point(aes_string(shape = legend, colour = legend)) +
    ylab("Performance Time (ms)") +
    scale_x_discrete() +
    scale_y_log10(breaks = round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/7)),7), 
                  labels = round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/7)),2))
  return(plot)
}

addMetricValue <- function(data, filteredResult, metric) {
  modelMetrics <- subset(filteredResult, 
                         PhaseName == "CalcModelMetrics" & 
                           MetricName == metric)
  # names(data)[names(data) == "ModelMetric"] <- metric
  for (model in unique(modelMetrics$Model)) {
    for (submodel in unique(subset(modelMetrics, Model == model)$Submodel)){
      modelMetricValue <- modelMetrics[modelMetrics$Model == model & modelMetrics$Submodel == submodel, 
                                       "MetricValue"]
      data[data$Model == model & data$Submodel == submodel, metric] <- modelMetricValue
    }
  }
  return(data)
}

addMetricValueToColumn <- function(data, filteredResult, metric, tool, size, case) {
  print(metric)
  modelMetrics <- subset(filteredResult, 
                         PhaseName == "CalcModelMetrics" & 
                           MetricName == metric &
                           RunIndex == 3)
  # names(data)[names(data) == "ModelMetric"] <- metric
  for (model in unique(modelMetrics$Model)) {
    for (submodel in unique(subset(modelMetrics, Model == model)$Submodel)){
      modelMetricValue <- modelMetrics[modelMetrics$Model == model & 
                                         modelMetrics$Submodel == submodel, 
                                       "MetricValue"]
      data[data$Model == model & data$Submodel == submodel & data$Tool == tool &
             data$Size == size & data$CaseName == case, metric] <- modelMetricValue
    }
  }
  return(data)
}

renameModels <- function(data){
  data[data$Model == "Schedule-Scale-Free-Hom", "Model"] <- "Scale-Free"
  data[data$Model == "Schedule-Hierarchical", "Model"] <- "Hierarchical"
  data[data$Model == "Schedule-Random", "Model"] <- "Random"
  data[data$Model == "Schedule-Watts-Strogatz-01", "Model"] <- "WS-0.1"
  data[data$Model == "Schedule-Watts-Strogatz-001", "Model"] <- "WS-0.01"
  data[data$Model == "Schedule-Watts-Strogatz-0001", "Model"] <- "WS-0.001"
  return(data)
}

#results <- read.csv(file = "../results/round3/results.csv", header = T, sep = ",", quote = "")
results$Submodel <- as.character(results$Submodel)
results[results$Submodel == "A", "Submodel"]
results[results$Submodel == "A", "Submodel"] <- "K3"
results[results$Submodel == "B", "Submodel"] <- "K4"
results[results$Submodel == "C", "Submodel"] <- "K5"
results[results$Submodel == "D", "Submodel"] <- "K6"
results[results$Submodel == "E", "Submodel"] <- "K7"
names(results)[names(results) == "Submodel"] <- "ModelDensity"
scales <- subset(results, Tool == "Sesame" & 
                   PhaseName == "CalcModelMetrics" &
                   RunIndex == 1 &
                   Size %in% c(1,2,3,4,5) &
                   MetricName == "Density" &
#                    MetricName %in% c("AvgClusteringStation", "Betweenness", 
#                                      "AvgShortestPath", "MaxDegree", "Density") &
                   CaseName == "StationsPath")
scales$Model <- as.character(scales$Model)
scales[scales$Model == "Schedule-Scale-Free-Hom", "Model"] <- "Scale-Free"
scales[scales$Model == "Schedule-Hierarchical", "Model"] <- "Hierarchical"
scales[scales$Model == "Schedule-Random", "Model"] <- "Random"
scales[scales$Model == "Schedule-Watts-Strogatz-01", "Model"] <- "WS-0.1"
scales[scales$Model == "Schedule-Watts-Strogatz-001", "Model"] <- "WS-0.01"
scales[scales$Model == "Schedule-Watts-Strogatz-0001", "Model"] <- "WS-0.001"

sizes <- c(1, 2, 3, 4, 5)
densities <- c("K3", "K4", "K5", "K6", "K7")
for(s in sizes) {
  for (d in densities) {
    values <- scales[scales$MetricName == "Density" & scales$Size == s & scales$ModelDensity == d, "MetricValue"]
    max <- max(scales[scales$MetricName == "Density" & scales$Size == s & scales$ModelDensity == d, "MetricValue"])    
    print(s)
    print(d)
    print(sd(values))
    print(sd(values)/max)
    }
}
plot <- ggplot(scales, aes(x = Size, y = MetricValue)) +
  geom_point(aes_string(shape = "ModelDensity", colour = "ModelDensity")) +
  facet_grid(. ~ Model, scales = "free_y") +
  theme_bw() +
  xlab("Model size") +
  ylab("Density") +
  ggtitle("Density metric of the graph topologies")
plot

# ScheduleNavigations

tool <- "Blazegraph"
metric <- "AvgShortestPath"
filteredResult <- subset(results,
                           CaseName == "ScheduleNavigations" & 
                           RunIndex == 3 & 
                           PhaseName %in% c("Check", "CalcModelMetrics"))
data <- subset(filteredResult, PhaseName == "Check" & MetricName == "Time")
data <- ddply(data, c(names(data)), summarize, 
              ModelMetric = 0)
names(data)
# delete
# data <- ddply(data, c(names(data)), summarize, 
#               )
data <- subset(results, PhaseName != "Read")
print(nrow(results))
data <- subset(data, PhaseName != "CalcQueryMetrics")
data <- subset(data, PhaseName != "QueryResults")
data <- subset(data, MetricName != "Matches")
data <- subset(data, Tool != "Neo4j")
data <- subset(data, RunIndex != 1)
print(nrow(data))
tools <- unique(data$Tool)

data$Model <- as.character(data$Model)
data <- cbind(NumOfNodes = "X", data)
data$NumOfNodes <- as.character(data$NumOfNodes)
data <- cbind(NumOfEdges = "X", data)
data$NumOfEdges <- as.character(data$NumOfEdges)
data <- cbind(AvgOutgoingDegree = "X", data)
data$AvgOutgoingDegree <- as.character(data$AvgOutgoingDegree)
data <- cbind(MaxDegree = "X", data)
data$MaxDegree <- as.character(data$MaxDegree)
data <- cbind(AvgDegreeDist = "X", data)
data$AvgDegreeDist <- as.character(data$AvgDegreeDist)
data <- cbind(AvgDegree = "X", data)
data$AvgDegree <- as.character(data$AvgDegree)
data <- cbind(MaxOutgoingDegree = "X", data)
data$MaxOutgoingDegree <- as.character(data$MaxOutgoingDegree)
data <- cbind(AvgOutgoingDegreeDist = "X", data)
data$AvgOutgoingDegreeDist <- as.character(data$AvgOutgoingDegreeDist)
data <- cbind(HigherDegree = "X", data)
data$HigherDegree <- as.character(data$HigherDegree)
data <- cbind(HigherOutgoingDegree = "X", data)
data$HigherOutgoingDegree <- as.character(data$HigherOutgoingDegree)
data <- cbind(AvgClusteringStation = "X", data)
data$AvgClusteringStation <- as.character(data$AvgClusteringStation)
data <- cbind(AvgShortestPath = "X", data)
data$AvgShortestPath <- as.character(data$AvgShortestPath)
data <- cbind(Betweenness = "X", data)
data$Betweenness <- as.character(data$Betweenness)
for (tool in tools) {
  sizes <- unique(subset(data, Tool == tool)$Size)
  print(tool)
  print(sizes)
  for (size in sizes) {
    cases <- unique(subset(data, Tool == tool & Size == size)$CaseName)
    for (case in cases) {
      subData <- subset(data, Tool == tool & Size == size & CaseName == case)

      data <- addMetricValueToColumn(data, subData, "NumOfNodes", tool, size, case)
      data <- addMetricValueToColumn(data, subData, "NumOfEdges", tool, size, case)
      data <- addMetricValueToColumn(data, subData, "AvgOutgoingDegree", tool, size, case)
      data <- addMetricValueToColumn(data, subData, "MaxDegree", tool, size, case)
      data <- addMetricValueToColumn(data, subData, "AvgDegreeDist", tool, size, case)
      data <- addMetricValueToColumn(data, subData, "AvgDegree", tool, size, case)
      data <- addMetricValueToColumn(data, subData, "MaxOutgoingDegree", tool, size, case)
      data <- addMetricValueToColumn(data, subData, "AvgOutgoingDegreeDist", tool, size, case)
      data <- addMetricValueToColumn(data, subData, "HigherDegree", tool, size, case)
      data <- addMetricValueToColumn(data, subData, "HigherOutgoingDegree", tool, size, case)
      data <- addMetricValueToColumn(data, subData, "AvgClusteringStation", tool, size, case)
      data <- addMetricValueToColumn(data, subData, "AvgShortestPath", tool, size, case)
      data <- addMetricValueToColumn(data, subData, "Betweenness", tool, size, case)
    }
  }
}
print(nrow(data))
data <- subset(data, PhaseName != "CalcModelMetrics")
print(nrow(data))

unique(data$Size)

selections <- c(names(data))
selections
selections <- selections[selections != "RunIndex"]
selections <- selections[selections != "Sequence"]
selections <- selections[selections != "MetricValue"]
selections
data2 <- ddply(data, selections, summarize, 
              MetricValue = median(MetricValue))
print(nrow(data))
print(nrow(data2))
21360 * 3

names(data2)
selections <- selections[selections != "Scenario"]
selections <- selections[selections != "MetricName"]
data3 <- ddply(data2, selections)
data3 <- subset(data2, select = -c(Scenario, MetricName, Iteration))
names(data3)[names(data3) == "MetricValue"] <- "Time"
######
data$Model <- as.character(data$Model)
data <- addMetricValue(data, filteredResult, "NumOfNodes")
data <- addMetricValue(data, filteredResult, "NumOfEdges")
data <- addMetricValue(data, filteredResult, "AvgOutgoingDegree")
data <- addMetricValue(data, filteredResult, "MaxDegree")
data <- addMetricValue(data, filteredResult, "AvgDegreeDist")
data <- addMetricValue(data, filteredResult, "AvgDegree")
data <- addMetricValue(data, filteredResult, "MaxOutgoingDegree")
data <- addMetricValue(data, filteredResult, "AvgOutgoingDegreeDist")
data <- addMetricValue(data, filteredResult, "HigherDegree")
data <- addMetricValue(data, filteredResult, "HigherOutgoingDegree")
data <- addMetricValue(data, filteredResult, "AvgClusteringStation")
data <- addMetricValue(data, filteredResult, "AvgShortestPath")
data <- addMetricValue(data, filteredResult, "Betweenness")

write.csv(data3, file="results.csv", row.names=FALSE)


data <- renameModels(data)

data$MetricValue <- data$MetricValue / 10**6
minValue <- min(subset(data, MetricName == "Time")$MetricValue)
maxValue <- max(subset(data, MetricName == "Time")$MetricValue)

plot <- visualize(data = data, xAxis = "Tool", yAxis = "MetricValue", 
                  legend = "Model", minValue = minValue, maxValue = maxValue)
plot + facet_grid(Model ~ Size) +
  xlab(metric) +
  theme(legend.position="bottom", legend.direction = "horizontal") +
  ggtitle(label = tool) +
  geom_boxplot(aes(x = Tool, y = MetricValue))



fit <- lm(scale(MetricValue) ~  scale(AvgClusteringStation) +scale(MaxDegree)+scale(HigherDegree)+scale(AvgShortestPath), data=data)
fit2 <- lm(scale(MetricValue) ~ scale(NumOfEdges), data = data)
# plot(x = data$Betweenness, y = data$MetricValue)
# abline(fit)
summary(fit)













# summary(fit2)
# confint(fit, level=0.95)
# confint(fit2, level=0.95)
# fitted(fit)
# residuals(fit)
# anova(fit)
# influence(fit)
# fit <- lm(MetricValue ~  + AvgShortestPath + AvgClusteringStation, data=data)
# fit2 <- lm(MetricValue ~  + AvgShortestPath, data=data)
# anova(fit2, fit)
# fit
# plot(fit)



# abline(fit, col="red")
# fit
# summary(lm(MetricValue ~ ModelMetric, data = data))
