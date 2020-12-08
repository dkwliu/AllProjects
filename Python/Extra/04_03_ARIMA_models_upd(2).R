
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

## FIT AR(2) MODEL.

# Use Arima() function to fit AR(2) model.
# The ARIMA model of order = c(2,0,0) gives an AR(2) model.
# Use summary() to show AR(2) model and its parameters.
train.ar2 <- Arima(train.ts, order = c(2,0,0))
summary(train.ar2)

# Apply forecast() function to make predictions for ts with 
# AR model in validation set.   
train.ar2.pred <- forecast(train.ar2, h = nValid, level = 0)
train.ar2.pred

# Use Acf() function to create autocorrelation chart of AR(2) model residuals.
Acf(train.ar2$residuals, lag.max = 12, 
      main = "Autocorrelations of AR(2) Model Residuals")

# Plot ts data, AR model, and predictions for validation period.
plot(train.ar2.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), 
     main = "AR(2) Model", lwd = 2, flty = 5) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(train.ar2.pred$fitted, col = "blue", lwd = 2)
lines(valid.ts, col = "black", lwd = 2, lty = 1)
legend(1992,2300, legend = c("Ridership Time Series", 
                             "AR(2) Forecast for Training Period",
                             "AR(2) Forecast for Validation Period"), 
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


## FIT MA(2) MODEL.

# Use Arima() function to fit MA(2) model.
# The ARIMA model of order = c(0,0,2) gives an MA(2) model.
# Use summary() to show MA(2) model and its parameters.
train.ma2<- Arima(train.ts, order = c(0,0,2))
summary(train.ma2)

# Apply forecast() function to make predictions for ts with 
# MA model in validation set.    
train.ma2.pred <- forecast(train.ma2, h = nValid, level = 0)
train.ma2.pred

# Use Acf() function to create autocorrelation chart of MA(2) model residuals.
Acf(train.ma2$residuals, lag.max = 12, 
       main = "Autocorrelations of MA(2) Model Residuals")

# Plot ts data, MA model, and predictions for validation period.
plot(train.ma2.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), 
     main = "MA(2) Model", lwd = 2, flty = 5) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(train.ma2.pred$fitted, col = "blue", lwd = 2)
lines(valid.ts, col = "black", lwd = 2, lty = 1)
legend(1992,2300, legend = c("Ridership Time Series", 
                             "MA(2) Forecast for Training Period",
                             "MA(2) Forecast for Validation Period"), 
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


## FIT ARMA(2,2) MODEL.

# Use Arima() function to fit ARMA(2,2) model.
# The ARIMA model of order = c(2,0,2) gives an ARMA(2,2) model.
# Use summary() to show ARMA model and its parameters.
train.arma2 <- Arima(train.ts, order = c(2,0,2))
summary(train.arma2)

# Apply forecast() function to make predictions for ts with 
# ARMA model in validation set.    
train.arma2.pred <- forecast(train.arma2, h = nValid, level = 0)
train.arma2.pred

# Use Acf() function to create autocorrelation chart of ARMA(2,2) model residuals.
Acf(train.arma2$residuals, lag.max = 12, 
      main = "Autocorrelations of ARMA(2,2) Model Residuals")

# Plot ts data, ARMA model, and predictions for validation period.
plot(train.arma2.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), 
     main = "ARMA(2,2) Model", lwd = 2, flty = 5) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(train.arma2.pred$fitted, col = "blue", lwd = 2)
lines(valid.ts, col = "black", lwd = 2, lty = 1)
legend(1992,2300, legend = c("Ridership Time Series", 
                             "ARMA(2,2) Forecast for Training Period",
                             "ARMA(2,2) Forecast for Validation Period"), 
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


## FIT ARIMA(2,1,2) MODEL.

# Use Arima() function to fit ARIMA(2,1,2) model.
# Use summary() to show ARIMA model and its parameters.
train.arima <- Arima(train.ts, order = c(2,1,2)) 
summary(train.arima)

# Apply forecast() function to make predictions for ts with 
# ARIMA model in validation set.    
train.arima.pred <- forecast(train.arima, h = nValid, level = 0)
train.arima.pred

# Using Acf() function, create autocorrelation chart of ARIMA(2,1,2) model residuals.
Acf(train.arima$residuals, lag.max = 12, 
    main = "Autocorrelations of ARIMA(2,1,2) Model Residuals")

# Plot ts data, ARIMA model, and predictions for validation period.
plot(train.arima.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), 
     main = "ARIMA(2,1,2) Model", lwd = 2, flty = 5) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(train.arima.pred$fitted, col = "blue", lwd = 2)
lines(valid.ts, col = "black", lwd = 2, lty = 1)
legend(1992,2300, legend = c("Ridership Time Series", 
                             "ARIMA Forecast for Training Period",
                             "ARIMA Forecast for Validation Period"), 
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


## FIT ARIMA(2,1,2)(1,1,2) MODEL.

# Use Arima() function to fit ARIMA(2,1,2)(1,1,2) model for 
# trend and seasonality.
# Use summary() to show ARIMA model and its parameters.
train.arima.seas <- Arima(train.ts, order = c(2,1,2), 
                                    seasonal = c(1,1,2)) 
summary(train.arima.seas)

# Apply forecast() function to make predictions for ts with 
# ARIMA model in validation set.    
train.arima.seas.pred <- forecast(train.arima.seas, h = nValid, level = 0)
train.arima.seas.pred

# Use Acf() function to create autocorrelation chart of ARIMA(2,1,2)(1,1,2) 
# model residuals.
Acf(train.arima.seas$residuals, lag.max = 12, 
    main = "Autocorrelations of ARIMA(2,1,2)(1,1,2) Model Residuals")

# Plot ts data, ARIMA model, and predictions for validation period.
plot(train.arima.seas.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), 
     main = "ARIMA(2,1,2)(1,1,2)[12] Model", lwd = 2, flty = 5) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(train.arima.seas.pred$fitted, col = "blue", lwd = 2)
lines(valid.ts, col = "black", lwd = 2, lty = 1)
legend(1992,2300, legend = c("Ridership Time Series", 
                             "Seasonal ARIMA Forecast for Training Period",
                             "Seasonal ARIMA Forecast for Validation Period"), 
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


## FIT AUTO ARIMA MODEL.

# Use auto.arima() function to fit ARIMA model.
# Use summary() to show auto ARIMA model and its parameters.
train.auto.arima <- auto.arima(train.ts)
summary(train.auto.arima)

# Apply forecast() function to make predictions for ts with 
# auto ARIMA model in validation set.  
train.auto.arima.pred <- forecast(train.auto.arima, h = nValid, level = 0)
train.auto.arima.pred

# Using Acf() function, create autocorrelation chart of auto ARIMA 
# model residuals.
Acf(train.auto.arima$residuals, lag.max = 12, 
    main = "Autocorrelations of Auto ARIMA Model Residuals")

# Plot ts data, trend and seasonality data, and predictions for validation period.
plot(train.auto.arima.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), 
     main = "Auto ARIMA Model", lwd = 2, flty = 5) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(train.auto.arima.pred$fitted, col = "blue", lwd = 2)
lines(valid.ts, col = "black", lwd = 2, lty = 1)
legend(1992,2300, legend = c("Ridership Time Series", 
                            "Auto ARIMA Forecast for Training Period",
                            "Auto ARIMA Forecast for Validation Period"), 
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
# for validation period forecast:
# (1) AR(2) model; 
# (2) MA(2) model; 
# (3) ARMA(2,2) model; 
# (4) ARIMA(2,1,2) model; 
# (5) ARIMA(2,1,2)(1,1,2) model; and 
# (6) Auto ARIMA model.
round(accuracy(train.ar2.pred, valid.ts), 3)
round(accuracy(train.ma2.pred, valid.ts), 3)
round(accuracy(train.arma2.pred, valid.ts), 3)
round(accuracy(train.arima.pred, valid.ts), 3)
round(accuracy(train.arima.seas.pred, valid.ts), 3)
round(accuracy(train.auto.arima.pred, valid.ts), 3)


## FIT SEASONAL ARIMA AND AUTO ARIMA MODELS FOR ENTIRE DATA SET. 
## FORECAST AND PLOT DATA, AND MEASURE ACCURACY.


## FIT SEASONAL ARIMA FOR ENTIRE DATA SET. 

# Use arima() function to fit seasonal ARIMA(2,1,2)(1,1,2) model 
# for entire data set.
# use summary() to show auto ARIMA model and its parameters for entire data set.
arima.seas <- Arima(ridership.ts, order = c(2,1,2), 
                                  seasonal = c(1,1,2)) 
summary(arima.seas)

# Apply forecast() function to make predictions for ts with 
# seasonal ARIMA model for the future 12 periods. 
arima.seas.pred <- forecast(arima.seas, h = 12, level = 0)
arima.seas.pred

# Use Acf() function to create autocorrelation chart of seasonal ARIMA 
# model residuals.
Acf(arima.seas$residuals, lag.max = 12, 
    main = "Autocorrelations of Seasonal ARIMA Model Residuals")

# Plot historical data, predictions for historical data, and seasonal 
# ARIMA forecast for 12 future periods.
plot(ridership.ts, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), lwd = 2,
     main = "Auto ARIMA Model for Entire Dataset") 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(arima.seas$fitted, col = "blue", lwd = 2)
lines(arima.seas.pred$mean, col = "blue", lty = 5, lwd = 2)
legend(1992,2300, legend = c("Ridership Series", 
                             "Seasonal ARIMA Forecast", 
                             "Seasonal ARIMA Forecast for 12 Future Periods"), 
       col = c("black", "blue" , "blue"), 
       lty = c(1, 1, 5), lwd =c(2, 2, 2), bty = "n")

# plot on the chart vertical lines and horizontal arrows
# describing training and future prediction intervals.
# lines(c(2004.25 - 3, 2004.25 - 3), c(0, 2600))
lines(c(2004.25, 2004.25), c(0, 2600))
text(1997.25, 2550, "Training")
text(2005.75, 2550, "Future")
arrows(2004.5 - 0.5, 2450, 1990.6, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5, 2450, 2006.75, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)


## FIT AUTO ARIMA MODELS FOR ENTIRE DATA SET. 

# Use auto.arima() function to fit ARIMA model for entire data set.
# use summary() to show auto ARIMA model and its parameters for entire data set.
auto.arima <- auto.arima(ridership.ts)
summary(auto.arima)

# Apply forecast() function to make predictions for ts with 
# auto ARIMA model for the future 12 periods. 
auto.arima.pred <- forecast(auto.arima, h = 12, level = 0)
auto.arima.pred

# Use Acf() function to create autocorrelation chart of auto ARIMA 
# model residuals.
Acf(auto.arima$residuals, lag.max = 12, 
    main = "Autocorrelations of Auto ARIMA Model Residuals")

# Plot historical data, predictions for historical data, and Auto ARIMA 
# forecast for 12 future periods.
plot(ridership.ts, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), lwd = 2,
     main = "Auto ARIMA Model for Entire Dataset") 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(auto.arima$fitted, col = "blue", lwd = 2)
lines(auto.arima.pred$mean, col = "blue", lty = 5, lwd = 2)
legend(1992,2300, legend = c("Ridership Series", 
                             "Auto ARIMA Forecast", 
                             "Auto ARIMA Forecast for 12 Future Periods"), 
       col = c("black", "blue" , "blue"), 
       lty = c(1, 1, 5), lwd =c(2, 2, 2), bty = "n")

# plot on the chart vertical lines and horizontal arrows
# describing training and future prediction intervals.
# lines(c(2004.25 - 3, 2004.25 - 3), c(0, 2600))
lines(c(2004.25, 2004.25), c(0, 2600))
text(1997.25, 2550, "Training")
text(2005.75, 2550, "Future")
arrows(2004.5 - 0.5, 2450, 1990.6, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)
arrows(2004.5, 2450, 2006.75, 2450, code = 3, length = 0.1,
       lwd = 1, angle = 30)


# MEASURE FORECAST ACCURACY FOR ENTIRE DATA SET.

# Use accuracy() function to identify common accuracy measures for:
# (1) Seasonal ARIMA (2,1,2)(1,1,2) Model,
# (1) Auto ARIMA Model,
# (2) Seasonal naive forecast, and
# (3) Naive forecast.
round(accuracy(arima.seas.pred$fitted, ridership.ts), 3)
round(accuracy(auto.arima.pred$fitted, ridership.ts), 3)
round(accuracy((snaive(ridership.ts))$fitted, ridership.ts), 3)
round(accuracy((naive(ridership.ts))$fitted, ridership.ts), 3)