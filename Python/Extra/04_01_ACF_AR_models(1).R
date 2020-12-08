
## USE FORECAST LIBRARY.

library(forecast)


## CREATE DATA FRAME. 

# Set working directory for locating files.
setwd("C:/misc/673_BAN/module4_arima")

# Create data frame.
Amtrak.data <- read.csv("Amtrak.csv")

# See the first 6 records of the file.
head(Amtrak.data)

## USE ts() FUNCTION TO CREATE TIME SERIES DATA SET.
## USE Acf() FUNCTION TO IDENTIFY AUTOCORRELATION.

# Function ts() takes three arguments: start, end, and freq.
# With monthly data, frequency (freq) of periods in a season (year) is 12. 
# With quarterly data, frequency in a season (year) is equal to 4.
# Arguments start and end are pairs: (season number, period number).
ridership.ts <- ts(Amtrak.data$Ridership, 
            start = c(1991, 1), end = c(2004, 3), freq = 12)

# Use Acf() function to identify autocorrelation and plot autocorrelation
# for different lags (up to maximum of 12).
Acf(ridership.ts, lag.max = 12, main = "Autocorrelation for Amtrak Ridership")

## CREATE TIME SERIES PARTITION.

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

# Use Acf() function to identify autocorrelation for training and validation
# data sets, and plot autocorrelation for different lags (up to maximum of 12)
Acf(train.ts, lag.max = 12, main = "Autocorrelation for Amtrak Training Data Set")
Acf(valid.ts, lag.max = 12, main = "Autocorrelation for Amtrak Validation Data Set")


## FIT REGRESSION MODEL WITH QUADRATIC TREND AND SEASONALITY. 
## USE Acf() FUNCTION TO IDENTIFY AUTOCORRELATION FOR RESIDUALS.
## PLOT RESIDUALS.

# Use tslm() function to create quadratic trend and seasonal model.
train.trend.season <- tslm(train.ts ~ trend + I(trend^2) + season)

# See summary of linear trend equation and associated parameters.
summary(train.trend.season)

# Apply forecast() function to make predictions for ts with 
# trend and seasonal model in validation set.  
train.trend.season.pred <- forecast(train.trend.season, h = nValid, level = 0)
train.trend.season.pred

# plot ts data, trend and seasonality data, and predictions for validation period.
plot(train.trend.season.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), 
     main = "Regression with Quadratic Trend and Seasonality", lwd = 2, flty = 5) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(train.trend.season.pred$fitted, col = "blue", lwd = 2)
lines(valid.ts, col = "black", lwd = 2, lty = 1)
legend(1992,2300, legend = c("Ridership Time Series", "Regression for Training Data",
                             "Forecast for Validation Data"), 
       col = c("black", "blue" , "blue"), 
       lty = c(1, 1, 5), lwd =c(2, 2, 2), bty = "n")

# Plot on the chart vertical lines and horizontal arrows
# describing training, validation, and future prediction intervals.
lines(c(2004.25 - 3, 2004.25 - 3), c(0, 2600))
lines(c(2004.25, 2004.25), c(0, 2600))
text(1996.25, 2550, "Training")
text(2002.75, 2550, "Validation")
arrows(2004.5 - 3.5, 2450, 1990.6, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5 - 3, 2450, 2004, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)


# Plot residuals of the predictions with trend and seasonality.
plot(train.trend.season.pred$residuals, 
     xlab = "Time", ylab = "Residuals", ylim = c(-400, 500), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), 
     main = "Regresssion Residuals for Training and Validation Data", 
     col = "brown", lwd = 2) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(valid.ts - train.trend.season.pred$mean, col = "brown", lwd = 2, lty = 1)

# Plot on the chart vertical lines and horizontal arrows
# describing training, validation, and future prediction intervals.
lines(c(2004.25 - 3, 2004.25 - 3), c(-450, 500))
lines(c(2004.25, 2004.25), c(-450, 500))
text(1996.25, 480, "Training")
text(2002.75, 480, "Validation")
arrows(2004.5 - 3.5, 410, 1990.6, 410, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5 - 3, 410, 2004, 410, code = 3, length = 0.1,
       lwd = 1, angle = 30)

# Use Acf() function to identify autocorrelation for the model residuals 
# (training and validation sets), and plot autocorrelation for different 
# lags (up to maximum of 12).
Acf(train.trend.season.pred$residuals, lag.max = 12, 
    main = "Autocorrelation for Amtrak Training Residuals")
Acf(valid.ts - train.trend.season.pred$mean, lag.max = 12, 
    main = "Autocorrelation for Amtrak Validation Residuals")


## USE Arima() FUNCTION TO CREATE AR(1) MODEL FOR TRAINING RESIDUALS.
## CREATE TWO-LEVEL MODEL WITH QUADRATIC TREND AND SEASONALITY MODEL 
## AND AR(1) RESIDUALS.
## PLOT DATA AND IDENTIFY ACCURACY MEASURES.

# Use Arima() function to fit AR(1) model for training residuals. The Arima model of 
# order = c(1,0,0) gives an AR(1) model.
# Use summary() to identify parameters of AR(1) model. 
res.ar1 <- Arima(train.trend.season$residuals, order = c(1,0,0))
summary(res.ar1)
res.ar1$fitted

# Use forecast() function to make prediction of residuals in validation set.
res.ar1.pred <- forecast(res.ar1, h = nValid, level = 0)
res.ar1.pred


# Develop a data frame to demonstrate the training AR model results 
# vs. original training series, training regression model, 
# and its residuals.  
train.df <- data.frame(train.ts, train.trend.season$fitted, 
            train.trend.season$residuals, res.ar1$fitted, res.ar1$residuals)
names(train.df) <- c("Ridership", "Regression", "Residuals",
                     "AR.Model", "AR.Model.Residuals")
train.df

# Plot residuals of the predictions for training data before AR(1).
plot(train.trend.season.pred$residuals, 
     xlab = "Time", ylab = "Residuals", ylim = c(-400, 500), bty = "l",
     xaxt = "n", xlim = c(1991, 2002), 
     main = "Regresssion Residuals for Training Data before AR(1)", 
     col = "brown", lwd = 3) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))

# Plot on the chart vertical lines and horizontal arrows
# describing training, validation, and future prediction intervals.
lines(c(2004.25 - 3, 2004.25 - 3), c(-450, 500))
lines(c(2004.25, 2004.25), c(-450, 500))
text(1996.25, 480, "Training")
arrows(2004.5 - 3.5, 410, 1990.6, 410, code = 3, length = 0.1,
       lwd = 1, angle = 30)


# Plot residuals of the residuals for training data after AR(1).
plot(res.ar1$residuals, 
     xlab = "Time", ylab = "Residuals", ylim = c(-400, 500), bty = "l",
     xaxt = "n", xlim = c(1991, 2002), 
     main = "Residuals of Residuals for Training Data after AR(1)", 
     col = "brown", lwd = 3) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))

# Plot on the chart vertical lines and horizontal arrows
# describing training, validation, and future prediction intervals.
lines(c(2004.25 - 3, 2004.25 - 3), c(-450, 500))
lines(c(2004.25, 2004.25), c(-450, 500))
text(1996.25, 480, "Training")
arrows(2004.5 - 3.5, 410, 1990.6, 410, code = 3, length = 0.1,
       lwd = 1, angle = 30)

# Use Acf() function to identify autocorrelation for the training 
# residual of residuals and plot autocorrelation for different lags 
# (up to maximum of 12).
Acf(res.ar1$residuals, lag.max = 12, 
    main = "Autocorrelation for Amtrak Training Residuals of Residuals")

# Create two-level model's forecast with quadratic trend and seasonality 
# regression + AR(1) for residuals for validation period.

# Create data table with validation data, regression forecast
# for validation period, AR(1) residuals for validation, and 
# two level model results. 
valid.two.level.pred <- train.trend.season.pred$mean + res.ar1.pred$mean

valid.df <- data.frame(valid.ts, train.trend.season.pred$mean, 
            res.ar1.pred$mean, valid.two.level.pred)
names(valid.df) <- c("Ridership", "Reg.Forecast", 
                  "AR(1)Forecast", "Combined.Forecast")
valid.df

# Use accuracy() function to identify common accuracy measures for validation period forecast:
# (1) two-level model (quadratic trend and seasonal model + AR(1) model for residuals),
# (2) quadratic trend and seasonality model only.
round(accuracy(valid.two.level.pred, valid.ts), 3)
round(accuracy(train.trend.season.pred$mean, valid.ts), 3)




## FIT REGRESSION MODEL WITH QUADRATIC TREND AND SEASONALITY 
## FOR ENTIRE DATASET. FORECAST AND PLOT DATA, AND MEASURE ACCURACY.

# Use tslm() function to create quadratic trend and seasonality model.
trend.season <- tslm(ridership.ts ~ trend + I(trend^2) + season)

# See summary of linear trend equation and associated parameters.
summary(trend.season)

# Apply forecast() function to make predictions with quadratic trend and seasonal 
# model into the future 12 months.  
trend.season.pred <- forecast(trend.season, h = 12, level = 0)
trend.season.pred


# Use Arima() function to fit AR(1) model for regression residuals.
# The ARIMA model order of order = c(1,0,0) gives an AR(1) model.
# Use forecast() function to make prediction of residuals into the future 12 months.
residual.ar1 <- Arima(trend.season$residuals, order = c(1,0,0))
residual.ar1.pred <- forecast(residual.ar1, h = 12, level = 0)


# Use summary() to identify parameters of AR(1) model.
summary(residual.ar1)

# Use Acf() function to identify autocorrealtion for the residual of residuals 
# and plot autocorrelation for different lags (up to maximum of 12).
Acf(residual.ar1$residuals, lag.max = 12, 
    main = "Autocorrelation for Amtrak Residuals of Residuals for Entire Data Set")


# Identify forecast for the future 12 periods as sum of quadratic trend and seasonal model
# and AR(1) model for residuals.
trend.season.ar1.pred <- trend.season.pred$mean + residual.ar1.pred$mean
trend.season.ar1.pred


# Create a data table with quadratic trend and seasonal forecast for 12 future periods,
# AR(1) model for residuals for 12 future periods, and combined two-level forecast for
# 12 future periods. 
table.df <- data.frame(trend.season.pred$mean, 
                    residual.ar1.pred$mean, trend.season.ar1.pred)
names(table.df) <- c("Reg.Forecast", "AR(1)Forecast","Combined.Forecast")
table.df


# plot historical data, predictions for historical data, and forecast for 12 future periods.
plot(ridership.ts, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), lwd = 2,
     main = "Two-Level Forecast: Regression with Trend and Seasonlity + AR(1)
     for Residuals") 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(trend.season$fitted + residual.ar1$fitted, col = "blue", lwd = 2)
lines(trend.season.ar1.pred, col = "blue", lty = 5, lwd = 2)
legend(1992,2300, legend = c("Ridership Series (Training Data)", 
                             "Two-Level Forecast for Training Data", "Two-Level Forecast for 12 Future Periods"), 
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


# Use accuracy() function to identify common accuracy measures for:
# (1) two-level model (quadratic trend and seasonal model + AR(1) model for residuals),
# (2) quadratic trend and seasonality model only and
# (3) seasonal naive forecast. 
round(accuracy(trend.season$fitted + residual.ar1$fitted, ridership.ts), 3)
round(accuracy(trend.season$fitted, ridership.ts), 3)
round(accuracy((snaive(ridership.ts))$fitted, ridership.ts), 3)