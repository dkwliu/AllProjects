
## USE FORECAST AND ZOO LIBRARIES.

library(forecast)
library(zoo)


## CREATE DATA FRAME. 

# Set working directory for locating files.
setwd("C:/misc/673_BAN/module2_smoothing")

# Create data frame.
Amtrak.data <- read.csv("Amtrak.csv")

# See the first 6 records of the file.
head(Amtrak.data)

## CREATE TIME SERIES DATA SET.

# Function ts() takes three arguments: start, end, and freq.
# With monthly data, frequency (freq) of periods in a season (year) is 12. 
# With quarterly data, frequency in a season (year) is equal to 4.
# Arguments start and end are pairs: (season number, period number).
ridership.ts <- ts(Amtrak.data$Ridership, 
                   start = c(1991, 1), end = c(2004, 3), freq = 12)
head(ridership.ts)


## DE-TRENDING and DE-SEASONALIZING TIME SERIES USING REGRESSION
## CREATE TRAILING MA USING RESIDUALS.
## FORECAST USING REGRESSION AND TRAILING MA INTO FUTURE PERIODS.

# Fit a regression model with quadratic trend and seasonality.
reg.trend.seas <- tslm(ridership.ts ~ trend + I(trend^2) + season)
summary(reg.trend.seas)


# Create forecast for the 12 periods into the future.
reg.trend.seas.pred <- forecast(reg.trend.seas, h = 12, level = 0)
reg.trend.seas.pred

# Identify and display residuals for time series based on the regression
# (differences between actual and regression values in the same periods).
reg.trend.seas.res <- reg.trend.seas$residuals
reg.trend.seas.res

# Apply trailing MA with 12 periods in the window to residuals.
ma.trailing.res_12 <- rollmean(reg.trend.seas.res, k = 12, align = "right")
ma.trailing.res_12

# Create forecast for residuals for the 12 periods into the future.
ma.trailing.res_12.pred <- forecast(ma.trailing.res_12, h = 12, level = 0)
ma.trailing.res_12.pred


# To develop real forecast for 12 periods into the future, 
# combine regression forecast and trailing MA forecast for residuals.
ts.forecast.12 <- reg.trend.seas.pred$mean + ma.trailing.res_12.pred$mean
ts.forecast.12

# Create a table with regression forecast, trailing MA for residuals
# and total forecast for 12 months into the future.
total.reg.ma.pred <- data.frame(reg.trend.seas.pred$mean, ma.trailing.res_12.pred$mean, 
                                ts.forecast.12)
total.reg.ma.pred

# Use accuracy() function to identify common accuracy measures.
# Use round() function to round accuracy measures to three decimal digits.
round(accuracy(reg.trend.seas.pred$fitted, ridership.ts), 3)
round(accuracy(reg.trend.seas.pred$fitted+ma.trailing.res_12, ridership.ts), 3)



## GENERATE PLOT OF ORIGINAL DATA AND REGRESSION FORECAST, AND PREDICTIONS INTO
## 12 PERIODS IN THE FUTUrE.
## GENERATE PLOT OF REGRESSION RESIDUALS, TRAILING MA FOR RESIDUALs, AND 
## TRAILING MA FORECAST INTO 12 PERIODS IN THE FUTURE.

# Plot original Ridership time series data and regression model.
plot(ridership.ts, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2400), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), lwd =2,
     main = "Ridership Series and Regression with Trend and Seasonality") 
axis(1, at = seq(1991, 2006.25, 1), labels = format(seq(1991, 2006.25, 1)))
lines(reg.trend.seas$fitted, col = "brown", lwd = 2)
lines(reg.trend.seas.pred$mean, col = "brown", lty =5, lwd = 2)
legend(1992,2300, legend = c("Ridership", "Regression",
                             "Regression Forecast for 12 Periods into Future"), 
       col = c("black", "brown" , "brown"), 
       lty = c(1, 1, 5), lwd =c(2, 2, 2), bty = "n")


# Plot regression residuals data and trailing MA based on residuals.
plot(reg.trend.seas.res, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(-250, 250), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), lwd =2, 
     main = "Regression Residuals and Trailing MA for Residuals, k =12") 
axis(1, at = seq(1991, 2006.25, 1), labels = format(seq(1991, 2006.25, 1)))
lines(ma.trailing.res_12, col = "blue", lwd = 2, lty = 1)
lines(ma.trailing.res_12.pred$mean, col = "blue", lwd = 2, lty = 5)
legend(1992, 230, legend = c("Regresssion Residuals", "Trailing MA for Residuals, k=12", 
                             "Trailing MA Forecast for 12 Periods into Future"), 
       col = c("black", "blue", "blue"), 
       lty = c(1, 1, 5), lwd =c(2, 2, 2), bty = "n")


## TRAILING MA USING DATA PARTITONING.

# Define the numbers of months in the training and validation sets,
# nTrain and nValid, respectively.
nValid <- 36
nTrain <- length(ridership.ts) - nValid
train.ts <- window(ridership.ts, start = c(1991, 1), end = c(1991, nTrain))
valid.ts <- window(ridership.ts, start = c(1991, nTrain + 1), 
                   end = c(1991, nTrain + nValid))

# Create trailing MA using training data and window size k=12.
ma.trailing <- rollmean(train.ts, k = 12, align = "right")

# Obtain the last MA in the trailing period (last.ma) and
# create forecast for the validation data (ma.trailing.pred).
last.ma <- tail(ma.trailing, 1)
ma.trailing.pred <- ts(rep(last.ma, nValid), start = c(1991, nTrain + 1),
                    end = c(1991, nTrain + nValid), freq = 12)

# Plot the predictions for trailing MA.
plot(train.ts, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xlim = c(1991, 2006.25), main = "Trainling MA, k=12 with Partition ") 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)) )
lines(ma.trailing, col = "brown", lwd = 2)
lines(ma.trailing.pred, col = "blue", lwd = 2, lty = 2)
lines(valid.ts)
legend(1993,2300, legend = c("Ridership", "Training MA, k=12",
                             "Validation MA, k= 12"), 
       col = c("black", "brown", "blue"), 
       lty = c(1, 1, 2), lwd =c(1, 2, 2), bty = "n")

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


## DEVELOP WEIGHTED MOVING AVERAGE (WMA).

# Use TTR library to apply WMA() - weighted moving average function.
# Need to define n = moving average window or number of periods 
# to average over, and also wts - weights for periods in
# WMA. The last weight in wts represents the most recent period in WMA,
# and the first weight - is the least recent.
library(TTR)

# Develop weighted moving average with n = 4 and n = 6
wma.trailing_4 <- WMA(ridership.ts, n = 4, 
                 wts = c(0.1, 0.2, 0.3, 0.4))
wma.trailing_6 <- WMA(ridership.ts, n = 6, 
                 wts = c(0.30, 0.25, 0.20, 0.15, 0.05, 0.05))

# Plot original data and trailing MA.
plot(ridership.ts, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2200), bty = "l",
     xlim = c(1991, 2006.25), main = "Weighted Moving Average") 
axis(1, at = seq(1991, 2004.25, 1), labels = format(seq(1991, 2004.25, 1)))
lines(wma.trailing_4, col = "brown", lwd = 2, lty = 1)
lines(wma.trailing_6, col = "blue", lwd = 2, lty = 5)
legend(1992,2200, legend = c("Ridership", "WMA, k=4", 
                             "WMA, k=6"), 
       col = c("black", "brown", "blue"), 
       lty = c(1, 1, 5), lwd =c(1, 2, 2), bty = "n")










