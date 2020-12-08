
## USE FORECAST LIBRARY.


library(forecast)


## CREATE DATA FRAME. 

# Set working directory for locating files.
setwd("C:/misc/673_BAN/module1_introduction")

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

## CReATE DATA PARTITION.
## PLOT DATA PARTITION.

# Define the numbers of months in the training and validation sets,
# nTrain and nValid, respectively.
nValid <- 36
nTrain <- length(ridership.ts) - nValid
train.ts <- window(ridership.ts, start = c(1991, 1), end = c(1991, nTrain))
valid.ts <- window(ridership.ts, start = c(1991, nTrain + 1), 
                   end = c(1991, nTrain + nValid))

# Plot the time series data and visualize partitions. 
plot(train.ts, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), main = "", lwd = 2) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(valid.ts, col = "black", lty = 1, lwd = 2)

# Plot on chart vertical lines and horizontal arrows describing
# training, validation, and future prediction intervals.
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


## FIT REGRESSION MODEL TO TIME SERIES.
## FORECAST USING VALIDATION SET.
## PLOT FORECASTS.

# Use tslm() function to fit a regression model (equation) to the time series 
# with linear trend (ridership.lin) and quadratic trend model (rideship.quad).
ridership.lin <- tslm(train.ts ~ trend)
ridership.quad <- tslm(train.ts ~ trend + I(trend^2))

# See summary of forecasting equation and associated parameters.
summary(ridership.lin)
summary(ridership.quad)

# Apply forecast() function to make predictions for ts data in
# training and validation sets.  
ridership.lin.pred <- forecast(ridership.lin, h = nValid, level = c(80, 95))
ridership.quad.pred <- forecast(ridership.quad, h = nValid, level = c(80, 95))

# Plot predictions for linear trend forecast.
plot(ridership.lin.pred$mean, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xlim = c(1991, 2006.25), main = "Linear Trend Forecast", 
     col = "blue", lwd =2) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)) )
lines(ridership.lin$fitted, col = "blue", lwd = 2)
lines(train.ts, col = "black", lty = 1)
lines(valid.ts, col = "black", lty = 1)

# Plot predictions for quadratic trend forecast.
plot(ridership.quad.pred$mean, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), main = "Quadratic Trend Forecast", 
     col = "blue", lwd =2) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)) )
lines(ridership.quad$fitted, col = "blue", lwd = 2)
lines(train.ts, col = "black", lty = 1)
lines(valid.ts, col = "black", lty = 1)

# Plot on chart vertical lines and horizontal arrows describing
# training, validation, and future prediction intervals.
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

## IDENTIFY FORECAST ACCURACY

# Use accuracy() function to identify common accuracy measures.
# Use round() function to round accuracy measures to three decimal digits.
round(accuracy(ridership.lin.pred$mean, valid.ts), 3)
round(accuracy(ridership.quad.pred$mean, valid.ts), 3)



## IDENTIFY NAIVE AND SEASONAL NAIVE FORECASTS.

# Use naive() to make naive forecast (ridership.naive.pred) 
# for validation data. 
# Use snaive() to make seasonal naive forecast (ridership.snaive.pred) for 
# validation data. 
ridership.naive.pred <- naive(train.ts, h = nValid)
ridership.snaive.pred <- snaive(train.ts, h = nValid)

# Plot the predictions for naive forecast.
plot(ridership.naive.pred$mean, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), main = "Naive Forecast", col = "blue", lwd =2) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(ridership.naive.pred$fitted, col = "blue", lwd = 2)
lines(train.ts, col = "black", lty = 1)
lines(valid.ts, col = "black", lty = 1)

# Plot the predictions for seasonal naive forecast.
plot(ridership.snaive.pred$mean, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), main = "Seasonal Naive Forecast", col = "blue", lwd =2) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(ridership.snaive.pred$fitted, col = "blue", lwd = 2)
lines(train.ts, col = "black", lty = 1)
lines(valid.ts, col = "black", lty = 1)

# Plot on chart vertical lines and horizontal arrows describing
# training, validation, and future prediction intervals.
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

## IDENTIFY FORECAST ACCURACY FOR NAIVE and SEASONAL NAIVAE FORECASTS.

# Use accuracy() function to identify common accuracy measures.
# Use round() function to round accuracy measures to three decimal digits.
round(accuracy(ridership.naive.pred$mean, valid.ts), 3)
round(accuracy(ridership.snaive.pred$mean, valid.ts), 3)


## IDENTIFY FORECAST ERRORS FOR QUADRATIC TREND FORECAST.
## PLOT FORECAST ERRORS FOR QUADRATIC TREND FORECAST.
## DEVELOP ERROR HISTOGRAM FOR QUADRATIC TREND FORECAST.

# Use $residuals element for the object ridership.quad.pred to
# identify training residuals.
ridership.train.res <- ridership.quad.pred$residuals
ridership.train.res

# Use the difference between actual and forecast validation data
# to identify validation errors (residuals).
ridership.valid.res <- valid.ts - ridership.quad.pred$mean
ridership.valid.res

# Plot forecast errors for quadratic trend forecast.
plot(ridership.valid.res, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(-450, 450), bty = "l",
     xlim = c(1991, 2006.25), 
     main = "Forecast Errors for Quadratic Trend Forecast", 
     col = "brown", lwd =2) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(ridership.train.res, col = "brown", lwd = 2)

# Plot on chart vertical lines and horizontal arrows describing
# training, validation, and future prediction intervals.
lines(c(2004.25 - 3, 2004.25 - 3), c(-500, 500))
lines(c(2004.25, 2004.25),  c(-500, 500))
text(1996.25, 470, "Training")
text(2002.75, 470, "Validation")
text(2005.75, 470, "Future")
arrows(2004.5 - 3.5, 430, 1990.6, 430, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5 - 3, 430, 2004, 430, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5, 430, 2006.75, 430, code = 3, length = 0.1,
       lwd = 1, angle = 30)

# Use hist() function to develop histogram for the model residuals.
hist(ridership.train.res, ylab = "Frequency", xlab = "Forecast Error",
     bty = "l", main = "Histogram of Quadratic Trend Forecast Errors", 
     col = "brown")


# Plot predictions for quadratic trend forecast with prediction interval
# for validation period.

plot(ridership.quad.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xlim = c(1991, 2006.25), 
     main = "Quadratic Trend Forecast with Prediction Interval (80% and 95%) ", 
     col = "black", flty = 2, lwd =2) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(ridership.quad$fitted, col = "blue", lwd = 2)
lines(valid.ts, col = "black", lwd =2, lty = 1)

# Plot on chart vertical lines and horizontal arrows describing
# training, validation, and future prediction intervals.
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