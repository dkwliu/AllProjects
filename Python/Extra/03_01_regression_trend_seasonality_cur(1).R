
## USE FORECAST LIBRARY.

library(forecast)

## CREATE DATA FRAME. 

# Set working directory for locating files.
setwd("C:/misc/673_BAN/module3_regression")

# Create data frame.
Amtrak.data <- read.csv("Amtrak.csv")

# See the first 6 records of the file.
head(Amtrak.data)



## USE ts() FUNCTION TO CREATE TIME SERIES DATA SET
## AND PARTITION DATA SET.

# Function ts() takes three arguments: start, end, and freq.
# With monthly data, frequency (freq) of periods in a season (year) is 12. 
# With quarterly data, frequency in a season (year) is equal to 4.
# Arguments start and end are pairs: (season number, period number).
ridership.ts <- ts(Amtrak.data$Ridership, 
            start = c(1991, 1), end = c(2004, 3), freq = 12)

# Define the numbers of months in the training and validation sets,
# nTrain and nValid, respectively.
# Total number of period length(ridership.ts) = 159.
# nvalid = 36 months for the last 12 months (April 2001 to March 2004).
# nTrain = 123 months, from January 1991 to March 2001.
nValid <- 36 
nTrain <- length(ridership.ts) - nValid
train.ts <- window(ridership.ts, start = c(1991, 1), end = c(1991, nTrain))
valid.ts <- window(ridership.ts, start = c(1991, nTrain + 1), 
                   end = c(1991, nTrain + nValid))




## FIT REGRESSION MODEL WITH LINEAR TREND: MODEL 1. 
## FORECAST AND PLOT DATA, AND MEASURE ACURACY.

# Use tslm() function (time series linear model) to create regression model with 
# linear trend.
train.lin <- tslm(train.ts ~ trend)

# See summary of linear trend model and associated parameters.
summary(train.lin)

# Apply forecast() function to make forecast for validation period.
train.lin.pred <- forecast(train.lin, h = nValid, level = 0)

# Plot ts data, linear trend and forecast for validation period.
plot(train.lin.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xlim = c(1991, 2006.25), main = "Linear Trend for Training and Validation Data", flty = 2) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)) )
lines(train.lin.pred$fitted, col = "blue", lwd = 2)
lines(valid.ts, col = "black", lty = 1)
legend(1992,2300, legend = c("Ridership Time Series", "Linear Regression for Training Data",
                             "Linear Forecast for Validation Data"), 
       col = c("black", "blue" , "blue"), 
       lty = c(1, 1, 5), lwd =c(2, 2, 2), bty = "n")

# Plot on the chart vertical lines and horizontal arrows
# describing training, validation, and future prediction intervals.
lines(c(2004.25 - 3, 2004.25 - 3), c(0, 2600))
lines(c(2004.25, 2004.25), c(0, 2600))
text(1996.25, 2550, "Training")
text(2002.75, 2550, "Validation")
text(2005.75, 2550, "Future")
arrows(2004.5 - 3.5, 2450, 1990.6, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5 - 3, 2450, 2004, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5, 2450, 2006.75, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)

# Use accuracy() function to identify common accuracy measures with rounded
# values to 3 decimals.
round(accuracy(train.lin.pred, valid.ts), 3)



## FIT REGRESSION MODEL WITH EXPONENTIAL TREND: MODEL 2. 
## FORECAST AND PLOT DATA, AND MEASURE ACCURACY.

# Use tslm() function to create regression model with exponential trend.
# If lambda = 0, tslm function applies Box-Cox transformation
# for log(y) - exponential trend.
# If lambda = 1, tslm function will just have a linear trend
# (the same as the original regression with linear trend, train.lin).
train.expo <- tslm(train.ts ~ trend, lambda = 0)

# See summary of exponential trend model and associated parameters.
summary(train.expo)

# Apply forecast() function to make forecast using exponential  
# trend for validation period.  
train.expo.pred <- forecast(train.expo, h = nValid, level = 0)

# Plot ts data, exponential and linear trends, and 
# respective forecasts for validation period.
plot(train.expo.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xlim = c(1991, 2006.25), main = "Linear and Exponential Regression Trends") 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(train.expo.pred$fitted, col = "blue", lwd = 2)
lines(train.lin.pred$fitted, col = "brown", lwd = 2, lty = 3)
lines(train.lin.pred$mean, col = "brown", lwd = 2, lty = 3)
lines(valid.ts, col = "black", lty = 1)
lines(train.ts, col = "black", lty = 1)
legend(1992,2300, legend = c("Ridership Time Series", 
                             "Exponentail Trend for Training and Validdation Data", 
                             "Linear Trend for Training and Validation Data"), 
       col = c("black", "blue" , "brown"), 
       lty = c(1, 1, 3), lwd =c(2, 2, 2), bty = "n")

# Plot on the chart vertical lines and horizontal arrows
# describing training, validation, and future prediction intervals.
lines(c(2004.25 - 3, 2004.25 - 3), c(0, 2600))
lines(c(2004.25, 2004.25), c(0, 2600))
text(1996.25, 2550, "Training")
text(2002.75, 2550, "Validation")
text(2005.75, 2550, "Future")
arrows(2004.5 - 3.5, 2450, 1990.6, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5 - 3, 2450, 2004, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5, 2450, 2006.75, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)

# Use accuracy() function to identify common accuracy measures for regression model
# with linear trend and exponential trend.
round(accuracy(train.lin.pred, valid.ts), 3)
round(accuracy(train.expo.pred, valid.ts), 3)



## FIT REGRESSION MODEL WITH QUADRATIC (POLYNOMIAL) TREND: MODEL 3. 
## FORECAST AND PLOT DATA, AND MEASURE ACCURACY.

# Use tslm() function to create quadratic (polynomial) trend model.
train.quad <- tslm(train.ts ~ trend + I(trend^2))

# See summary of quadratic trend model and associated parameters.
summary(train.quad)

# Apply forecast() function to make predictions for ts data in
# validation set.  
train.quad.pred <- forecast(train.quad, h = nValid, level = 0)

# Plot ts data, regression with quadratic trend and forecast for validation period.
plot(train.quad.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xlim = c(1991, 2006.25), main = "Quadratic Trend for Training and Validation Data", 
     flty = 2) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)) )
lines(train.quad.pred$fitted, col = "blue", lwd = 2)
lines(valid.ts, col = "black", lty = 1)
legend(1992,2300, legend = c("Ridership Time Series", "Quadratic Trend for Training Data",
                             "Quadratic Forecast for Validation Data"), 
       col = c("black", "blue" , "blue"), 
       lty = c(1, 1, 5), lwd =c(2, 2, 2), bty = "n")

# Plot on the chart vertical lines and horizontal arrows
# describing training, validation, and future prediction intervals.
lines(c(2004.25 - 3, 2004.25 - 3), c(0, 2600))
lines(c(2004.25, 2004.25), c(0, 2600))
text(1996.25, 2550, "Training")
text(2002.75, 2550, "Validation")
text(2005.75, 2550, "Future")
arrows(2004.5 - 3.5, 2450, 1990.6, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5 - 3, 2450, 2004, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5, 2450, 2006.75, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)

# Use accuracy() function to identify common accuracy measures
# for regression models with linear trend and quadratic (polynomial) trend.
round(accuracy(train.lin.pred, valid.ts), 3)
round(accuracy(train.quad.pred, valid.ts), 3)



## FIT REGRESSION MODEL WITH SEASONALITY: MODEL 4. 
## FORECAST AND PLOT DATA, AND MEASURE ACCURACY.

# Use tslm() function to create seasonal model.
train.season <- tslm(train.ts ~ season)

# See summary of seasonal model and associated parameters.
summary(train.season)

# If necessary, run the following code to identify seasons
train.season$data 

# Apply forecast() function to make predictions for ts with 
# seasonality data in validation set.  
train.season.pred <- forecast(train.season, h = nValid, level = 0)

# Plot ts data, regression model with seasonality, and forecast for validation period.
plot(train.season.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xlim = c(1991, 2006.25), main = "Model with Seasonality for Training and Validation Data", 
     flty = 5) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(train.season.pred$fitted, col = "blue", lwd = 2)
lines(valid.ts, col = "black", lty = 1)
legend(1992,2300, legend = c("Ridership Time Series", "Seasonality Model for Training Data",
                             "Seasonality Forecast for Validation Data"), 
       col = c("black", "blue" , "blue"), 
       lty = c(1, 1, 5), lwd =c(2, 2, 2), bty = "n")

# Plot on the chart vertical lines and horizontal arrows
# describing training, validation, and future prediction intervals.
lines(c(2004.25 - 3, 2004.25 - 3), c(0, 2600))
lines(c(2004.25, 2004.25), c(0, 2600))
text(1996.25, 2550, "Training")
text(2002.75, 2550, "Validation")
text(2005.75, 2550, "Future")
arrows(2004.5 - 3.5, 2450, 1990.6, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5 - 3, 2450, 2004, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5, 2450, 2006.75, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)

# Plot residuals of the model with seasonality.
plot(train.season.pred$residuals, 
     xlab = "Time", ylab = "Residuals", ylim = c(-400, 500), bty = "l",
     xlim = c(1991, 2006.25), main = "Residuals for the Seasonality Model", 
     col = "brown", lwd = 2) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(valid.ts - train.season.pred$mean, col = "brown", lty = 1, lwd=2)

# Plot on the chart vertical lines and horizontal arrows
# describing training, validation, and future prediction intervals.
lines(c(2004.25 - 3, 2004.25 - 3), c(-450, 500))
lines(c(2004.25, 2004.25), c(-450, 500))
text(1996.25, 480, "Training")
text(2002.75, 480, "Validation")
text(2005.75, 480, "Future")
arrows(2004.5 - 3.5, 410, 1990.6, 410, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5 - 3, 410, 2004, 410, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5, 410, 2006.75, 410, code = 3, length = 0.1,
       lwd = 1, angle = 30)

# Use accuracy() function to identify common accuracy measures
# for regression models with (1) linear trend, (2) quadratic (polynomial) trend,
# and (3) seasonality.
round(accuracy(train.lin.pred, valid.ts), 3)
round(accuracy(train.quad.pred, valid.ts), 3)
round(accuracy(train.season.pred, valid.ts), 3)



## FIT REGRESSION MODEL WITH QUADRATIC TREND AND SEASONALITY: MODEL 5. 
## FORECAST AND PLOT DATA, AND MEASURE ACCURACY.

# Use tslm() function to create quadratic trend and seasonal model.
train.trend.season <- tslm(train.ts ~ trend + I(trend^2) + season)

# See summary of quadratic trend and seasonality model and associated parameters.
summary(train.trend.season)

# Apply forecast() function to make predictions for ts with 
# trend and seasonality data in validation set.  
train.trend.season.pred <- forecast(train.trend.season, h = nValid, level = 0)

# Plot ts data, trend and seasonality data, and predictions for validation period.
plot(train.trend.season.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xlim = c(1991, 2006.25), main = "Model with Quadratic Trend and Monthly Seasonality", 
     flty = 5, lwd = 2) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(train.trend.season.pred$fitted, col = "blue", lwd = 2)
lines(valid.ts, col = "black", lty = 1, lwd = 2)
legend(1992,2300, legend = c("Ridership Time Series", 
                             "Trend and Seasonality Model for Training Data",
                             "Trend and Seasonality Forecast for Validation Data"), 
       col = c("black", "blue" , "blue"), 
       lty = c(1, 1, 5), lwd =c(2, 2, 2), bty = "n")

# Plot on the chart vertical lines and horizontal arrows
# describing training, validation, and future prediction intervals.
lines(c(2004.25 - 3, 2004.25 - 3), c(0, 2600))
lines(c(2004.25, 2004.25), c(0, 2600))
text(1996.25, 2550, "Training")
text(2002.75, 2550, "Validation")
text(2005.75, 2550, "Future")
arrows(2004.5 - 3.5, 2450, 1990.6, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5 - 3, 2450, 2004, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5, 2450, 2006.75, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)

# Plot residuals of predictions with trend and seasonality.
plot(train.trend.season.pred$residuals, 
     xlab = "Time", ylab = "Residuals", ylim = c(-400, 500), bty = "l",
     xlim = c(1991, 2006.25), main = "Residuals for Trend and Seasonality Model", 
     col = "brown", lwd = 2) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(valid.ts - train.trend.season.pred$mean, col = "brown", lty = 1, lwd=2)

# Plot on the chart vertical lines and horizontal arrows
# describing training, validation, and future prediction intervals.
lines(c(2004.25 - 3, 2004.25 - 3), c(-450, 500))
lines(c(2004.25, 2004.25), c(-450, 500))
text(1996.25, 480, "Training")
text(2002.75, 480, "Validation")
text(2005.75, 480, "Future")
arrows(2004.5 - 3.5, 410, 1990.6, 410, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5 - 3, 410, 2004, 410, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5, 410, 2006.75, 410, code = 3, length = 0.1,
       lwd = 1, angle = 30)

# Use accuracy() function to identify common accuracy measures
# for various regression models: (1)linear trend, (2) quadratic  
# (polynomial) trend, (3) seasonality, and (4) quadratic trend and seasonality.
round(accuracy(train.lin.pred, valid.ts),3)
round(accuracy(train.quad.pred, valid.ts),3)
round(accuracy(train.season.pred, valid.ts),3)
round(accuracy(train.trend.season.pred, valid.ts),3)


## FIT REGRESSION MODEL WITH QUADRATIC TREND AND SEASONALITY: ALTERNATIVE MODELS.
## FORECAST AND PLOT DATA, AND MEASURE ACCURACY.

# Develop seas object with January being the first month 
# in the model.
seas <- seasonaldummy(train.ts)
seas

# Create quadratic trend and seasonality model with January as
# the first month in the model.
train.trend.season1 <- tslm(train.ts ~ trend + I(trend^2) + seas)
summary(train.trend.season1)

# Develop data frame for validation period utilizing this model. 
forecast.param_1 <- data.frame(trend = c(124:159), 
  Jan = c(0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0), 
  Feb = c(0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0), 
  Mar = c(0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1), 
  Apr = c(1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0), 
  May = c(0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0), 
  Jun = c(0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0),
  Jul = c(0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0), 
  Aug = c(0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0), 
  Sep = c(0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0),
  Oct = c(0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0), 
  Nov = c(0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0))

forecast.param_1

# Apply forecast() function to make predictions for ts with 
# trend and seasonality data in validation set.  
train.trend.season1.pred <- forecast(train.trend.season1, 
                         newdata = forecast.param_1, level = 0)
train.trend.season1.pred


# Created quadratic trend and seasonality model with only specific 
# months (January-February, and then July-September).
seas[ ,c(1:2,7:9)]
      
train.trend.season2 <- tslm(train.ts ~ trend + I(trend^2) + seas[ ,c(1:2,7:9)])
summary(train.trend.season2)

# Develop data frame for validation period utilizing this model. 
forecast.param_2 <- data.frame(trend = c(124:159), 
  Jan = c(0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0), 
  Feb = c(0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0), 
  Jul = c(0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0), 
  Aug = c(0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0), 
  Sep = c(0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0))

forecast.param_2

# Apply forecast() function to make predictions for ts with 
# trend and seasonality data in validation set.  
train.trend.season2.pred <- forecast(train.trend.season2, 
                                     newdata = forecast.param_2, level = 0)
train.trend.season2.pred

# Use accuracy() function to identify common accuracy measures
# for various regression models: original quadratic trend and 
# seasonality and two alternative models for trend and seasonality. 
round(accuracy(train.trend.season.pred, valid.ts),3)
round(accuracy(train.trend.season1.pred, valid.ts),3)
round(accuracy(train.trend.season2.pred, valid.ts),3)


## FIT REGRESSION MODEL WITH QUADRATIC TREND AND SEASONALITY 
## FOR ENTIRE DATASET. FORECAST AND PLOT DATA, AND MEASURE ACCURACY.

# Use tslm() function to create quadratic trend and seasonality model.
trend.season <- tslm(ridership.ts ~ trend + I(trend^2) + season)

# See summary of quadratic trend and seasonality equation and associated parameters.
summary(trend.season)

# Apply forecast() function to make predictions for ts with 
# trend and seasonality data in 12 future periods.
trend.season.pred <- forecast(trend.season, h = 12, level = 0)

# Plot ts data, regression model with trend and seasonality data, and predictions 
# for future 12 periods.
plot(trend.season.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xlim = c(1991, 2006.25), lwd = 2,
     main = "Regression Model with Trend and Seasonality and Forecast for Future Periods", 
     flty = 5) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(trend.season.pred$fitted, col = "blue", lwd = 2)
legend(1992,2300, legend = c("Ridership Time Series", 
                             "Trend and Seasonality Model for Entire Data",
                             "Trend and Seasonality Forecast for Future 12 Periods"), 
       col = c("black", "blue" , "blue"), 
       lty = c(1, 1, 5), lwd =c(2, 2, 2), bty = "n")

# plot on the chart vertical lines and horizontal arrows
# describing training, validation, and future prediction intervals.
# lines(c(2004.25 - 3, 2004.25 - 3), c(0, 2600))
lines(c(2004.25, 2004.25), c(0, 2600))
text(1997.25, 2550, "Training")
text(2005.75, 2550, "Future")
arrows(2004.5 - 0.5, 2450, 1990.6, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5, 2450, 2006.75, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)



## COMPARE ACCURACY MEASURES OF REGRESSION FORECAST WITH QUANDRATIC TREND AND SEASONALITY 
## FOR THE ENTIRE DATA SET WITH ACCURACY MEASURES OF NAIVE FORECAST AND SEASONAL NAIVE 
## FORECAST FOR ENTIRE DATA SET.

# Use accuracy() function to identify common accuracy measures
# for naive model, seasonal naive, and regression model with quadratic trend and seasonality.
round(accuracy(trend.season.pred$fitted, ridership.ts),3)
round(accuracy((naive(ridership.ts))$fitted, ridership.ts), 3)
round(accuracy((snaive(ridership.ts))$fitted, ridership.ts), 3)




