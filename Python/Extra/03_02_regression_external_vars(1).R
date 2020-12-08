
## USE FORECAST LIBRARY.

library(forecast)

## CREATE DATA FRAME. 

# Set working directory for locating files.
setwd("C:/misc/673_BAN/module3_regression")

# Create data frame.
delin.data <- read.csv("delinquency_rate_3.csv")

# See the first 6 records of the file.
head(delin.data)
tail(delin.data)

## USE ts() FUNCTION TO CREATE TIME SERIES DATA SET.

# Function ts() takes three arguments: start, end, and freq.
# With quarterly data, frequency in a season (year) is equal to 4.
# Arguments start and end are pairs: (season number, period number).
delin.ts <- ts(delin.data$delin, 
            start = c(1991, 1), end = c(2018, 4), freq = 4)
ccredit.ts <- ts(delin.data$ccredit, 
                 start = c(1991, 1), end = c(2018, 4), freq = 4)
unemploy.ts <- ts(delin.data$unemploy, 
                 start = c(1991, 1), end = c(2018, 4), freq = 4)

## FIT VARIOUS REGRESSION MODELS FOR ENTIRE DATA SET. 
## FORECAST AND PLOT DATA AND REGRESSION MODELS.
## PROVIDE ACCURACY MEASURES FOR THESE MODELS.

# Use tslm() function to create linear trend (delin.lin) 
# model for time series data. Forecast for 4 periods into
# the future.
delin.lin <- tslm(delin.ts ~ trend)
summary(delin.lin)
delin.lin.pred <- forecast(delin.lin, h = 4, level = 0)
delin.lin.pred

# Use tslm() function to create quadratic trend (delin.quad)
# model for time series data. Forecast for 4 periods into
# the future.
delin.quad <- tslm(delin.ts ~ trend + I(trend^2))
summary(delin.quad)
delin.quad.pred <- forecast(delin.quad, h = 4, level = 0)
delin.lin.pred

# Use tslm() function to create quadratic trend and seasonality
# model (delin.quad.season) for time series data. Forecast for 4
# periods into the future.
delin.quad.season <- tslm(delin.ts ~ trend + I(trend^2) + season)
summary(delin.quad.season)
delin.quad.season.pred <- forecast(delin.quad.season, h = 4, level = 0)
delin.quad.pred

# Use plot() function to create plot with linear trend. 
plot(delin.ts, 
     xlab = "Time", ylab = "Delinquency (in %)",
     ylim = c (1, 8), main = "Delinquency with Linear Trend", 
     bty = "l", lwd = 1.5, col="blue")
axis(1, at = seq(1991, 2019, 1), labels = format(seq(1991, 2019, 1)))
lines(delin.lin$fitted, lwd = 2)

# Use plot() function to create plot with quadratic trend. 
plot(delin.ts, 
     xlab = "Time", ylab = "Delinquency (in %)",
     ylim = c (1, 8), main = "Delinquency with Quadratic Trend", 
     bty = "l", lwd = 1.5, col="blue")
axis(1, at = seq(1991, 2019, 1), labels = format(seq(1991, 2019, 1)))
lines(delin.quad$fitted, lwd = 2)

# Use plot() function to create plot with quadratic trend and
# seasonality.
plot(delin.ts, 
     xlab = "Time", ylab = "Delinquency (in %)",
     ylim = c (1, 8), main = "Delinquency with Quadratic Trend and Seasonality", 
     bty = "l", lwd = 1.5, col="blue")
axis(1, at = seq(1991, 2019, 1), labels = format(seq(1991, 2019, 1)))
lines(delin.quad.season$fitted, lwd = 2)


#Accuracy measures for the three forecasts: linear trend, 
# quadratic trend, and quadratic trend with seasonality. 
round(accuracy(delin.lin.pred$fitted, delin.ts),3)
round(accuracy(delin.quad.pred$fitted, delin.ts),3)
round(accuracy(delin.quad.season.pred$fitted, delin.ts),3)


## FIT REGRESSION MODEL WITH QUADRATIC TREND AND 2 EXTERNAL VARIABLES
## FOR ENTIRE DATASET. FORECAST DATA AND MEASURE FORECAST ACCURACY.

# Use tslm() function to create quadratic trend with 2 external variables.
delin.quad.external_2 <- tslm(delin.ts ~ trend + I(trend^2)
                  + ccredit.ts + unemploy.ts)

# See summary of the model with linear trend and external variables.
summary(delin.quad.external_2)

# Use tslm() function to create quadratic trend with 1 external variables.
delin.quad.external_1 <- tslm(delin.ts ~ trend + I(trend^2)
                             + ccredit.ts)

# See summary of the model with linear trend and external variables.
summary(delin.quad.external_1)


# To forecast 4 quarters of 2019, develop the values of the variables for those periods.
forecast_param <- data.frame(trend = c(113:116), ccredit.ts = c(2.2, 2.8, 3.9, 3.8))
newdata

# Apply forecast() function to make predictions for ts with 
# trend and external variables in 4 quarters of 2019.  
delin.external.pred <- forecast(delin.quad.external_1, newdata = forecast_param, level = 0)
delin.external.pred


# Measure accuracy of the forecast with quadratic trend and external 
# variable, and forecast with quadratic trend.
round(accuracy(delin.external.pred$fitted, delin.ts),3)
round(accuracy(delin.quad.pred$fitted, delin.ts),3)





