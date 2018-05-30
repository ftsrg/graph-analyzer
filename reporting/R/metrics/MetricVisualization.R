font.size <- 8
guide.legend <- guide_legend(ncol=1, override.aes = list(size = font.size), title.position = "top")
guides <- guides(col=guide.legend, fill=guide_legend)

deCamelCase <- function(s) {
  gsub("([A-Z])", " \\1", s)
}

theme <- theme(legend.position = "right",
               #legend.position = c(0.82, 0.2),
               axis.text = element_text(size = 14),
               text = element_text(size = 18)
              )

PlotsECDFByFileName <- function(dt,
                                plot.file.name,
                                col,
                                x = "Value",
                                group = "file.name",
                                facetwrap = "category",
                                scalex = F,
                                title){
  
  print(plot.file.name)
  pdf(plot.file.name, width = 12)
  base <- ggplot(as.data.frame(dt))
  base <- base +
    stat_ecdf(aes_string(x = x, col = col, group = group), geom = "step") +
    facet_wrap(facetwrap, drop  = T, scales = "free_y") +
    scale_color_brewer(palette = 'Set1') +
    xlab('') +
    ylab(deCamelCase(title)) +
    guides +
    theme_bw() +
    theme
  if(scalex) base <- base + scale_x_log10()
  print(base)
  dev.off()
}


PlotsECDFByFileNameOneSide <- function(dt, 
                                plot.file.name,
                                col,
                                x = "Value",
                                group = "file.name",
                                scalex = F,
                                title){
  print(plot.file.name)
  pdf(plot.file.name, width = 12)
  base <- ggplot(as.data.frame(dt))
  base <- base + stat_ecdf(aes_string(x = x, col = col, group = group), geom = "step") +
    scale_color_brewer(palette = 'Set1') +
    xlab('') +
    ylab(deCamelCase(title)) +
    guides +
    theme_bw() +
    theme
  if(scalex) base <- base + scale_x_log10()
  print(base)
  dev.off()
}

PlotsEcdfByFileNameGrid <- function(dt, plot.file.name,
                                    x = "Value",
                                    col = "Iscontainment",
                                    facetgrid = "Category",
                                    group = "file.name",
                                    scalex = F,
                                    title){
  print(plot.file.name)
  pdf(plot.file.name, width = 12)
  base <- ggplot(as.data.frame(dt))
  base <- base + stat_ecdf(aes_string(x = x, col = col, group = "file.name", linetype = "Iscontainment"), geom = "step") +
    facet_grid(facetgrid,drop  = T, scales = "free_y") +
    scale_color_brewer(palette = 'Set1')+
    xlab('') +
    ylab(deCamelCase(title)) +
    guides +
    theme_bw() +
    theme
  if(scalex) base <- base + scale_x_log10()
  print(base)
  dev.off()
}

PlotsBasicScatterplot <- function(dt, plot.file.name,
                                  x,y,
                                  col = "Iscontainment",
                                  facetwrap = "Category",
                                  scalex = F, scaley = F){
  print(plot.file.name)
  pdf(plot.file.name, width = 12)
  base <- ggplot(as.data.frame(dt))
  base <- base + geom_point(aes_string(x = x,y = y, col = col)) +
    facet_wrap(facetwrap,drop  = T, scales = "free_y") +
    scale_color_brewer(palette = 'Set1') +
    guides +
    theme_bw() +
    theme
  if(scalex) base <- base + scale_x_log10()
  if(scaley) base <- base + scale_y_log10()
  print(base)
  dev.off()
}



PlotsBasicScatterplotGrid <- function(dt, plot.file.name,
                                  x,y,
                                  col = "Iscontainment",
                                  facetgrid = "Category",
                                  scalex = F, scaley = F){
  print(plot.file.name)
  pdf(plot.file.name, width = 12)
  base <- ggplot(as.data.frame(dt))
  base <- base + geom_point(aes_string(x = x,y = y, col = col)) +
    facet_grid(facetgrid,drop  = T, scales = "free_y") +
    scale_color_brewer(palette = 'Set1') +
    theme_bw() +
    theme
  if(scalex) base <- base + scale_x_log10()
  if(scaley) base <- base + scale_y_log10()
  print(base)
  dev.off()
}


PlotsScatterplotByFileName <- function(dt, plot.file.name, x, y,
                                       label = "file.name",
                                    col = "category",
                                    scalex = T, scaley = T,
                                    shape = NULL){
  pdf(plot.file.name)
  df <- as.data.frame(dt)
  base <- ggplot(df)
  base <- base + geom_point(aes_string(x = x, y = y, col = col, shape = shape), size = font.size) +
    #geom_text_repel(aes_string(x = x, y = y, label = label, col = col)) +
    scale_color_brewer(palette = "Set1") +
    guides +
    theme_bw() +
    theme
  if(scalex) {
    base <- base + scale_x_continuous(trans = "log10")}
  if(scaley) {base <- base + scale_y_continuous(trans = "log10")}
  print(base)
  dev.off()
}

PlotsBoxplot <- function(dt, plot.file.name, x, y,
                         label = "file.name",
                         col = "category",
                         facetwrap = "file.name",
                         scalex = T, scaley = T){
  df <- as.data.frame(dt)
  pdf(plot.file.name, height = 12)
  base <- ggplot(df)
  base <- base + geom_boxplot(aes_string(x = x, y = y)) +
    facet_wrap(facetwrap, drop = T, scales = "free_y", nrow = 2) +
    theme_bw() +
    theme +
    theme(axis.text.x = element_blank()) +
    scale_fill_brewer(palette = 'Set1')
  if(scaley)  base <- base + scale_y_log10()
  print(base)
  dev.off()
}


PlotsBoxplotGrid <- function(dt, plot.file.name, x, y,
                         label = "file.name",
                         col = "category",
                         facetgrid = "file.name",
                         scalex = T, scaley = T){
  df <- as.data.frame(dt)
  pdf(plot.file.name, height = 12)
  base <- ggplot(df)
  base <- base + geom_boxplot(aes_string(x = x, y = y)) +
    facet_grid(facetgrid, drop = T, scales = "free") +
    theme_bw() +
    theme(axis.text.x = element_blank()) +
    scale_fill_brewer(palette = 'Set1')
  if(scaley)  base <- base + scale_y_log10()
  print(base)
  dev.off()
}
