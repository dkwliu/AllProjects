#BAN 673 Project
#Group Member:
#Ankit
#Chenwei Cao
#David Liu
#Sayali
#Chinnu Krish


#Use Libraries
library(forecast)
library(dplyr)

#Set working directory for locating files
setwd("~/Desktop/TimeSeries")

#***************************************Creating and cleaning data frame*******************************************************
#Combining both the previously divided trainig and testing dataset. The purpose in doing this is to
#clean both sets of data at once
combined.df <- rbind(train.df, test.df)
combined.df$date <- as.Date(combined.df$date)

# creating a set of dates starting from 2013-01-01 to 2017-04-24
# the purpose of this is to match these dates to the dates in the Delhi
# temperature dataset to check whether the dates in the time series is complete and contiguous
date.seq.df <- data.frame(date = c(seq(as.Date("2013-01-01"), as.Date("2017-04-24"), by="days")))

# comparing the dates in the entire climate dataset interval to the actual dates in the interval
all.equal(date.seq.df$date, combined.df$date)

# since there are more dates in the climate dataset, remove row with duplicate date
combined.cleaned.df <- data.frame(combined.df)
length(combined.cleaned.df$date)

climate.df <- distinct(combined.cleaned.df, date, .keep_all = TRUE)
length(climate.df$date)

all.equal(date.seq.df$date, climate.df$date)

# check to see if any mean temperature values are null
any(is.na(climate.df$meantemp))


# Viewing the data
climate.df




#*****************************************Visualizing the time series ********************************************************
#USE ts() FUNCTION TO CREATE TIME SERIES DATA SET
#set the frq = 1
climate.ts <- ts(climate.df$meantemp, start = 1, freq = 1)
climate.ts

# Use plot function to plot all daily mean temperature data. The data ranges from
# 1/1/2013 to 4/24/2017
plot(climate.ts, 
     xlab = "2013-01-01 to 2017-04-24", ylab = "Mean Temperature (Celsius)",
     ylim = c (0,40), main = "Delhi Mean Temperature Time Series (Training Data)")

# checking the autocorrelation values of the mean temperature data
climate.acf <- Acf(climate.ts, lag.max = 365, 
                   main = "Autocorrelation for Delhi Climate Data (Mean Temperature)") 

# checking the autocorrelation values of the mean temperature data with diff of lag 1
climate.ts.diff <- diff(climate.ts, lag = 1)
climate.ts.diff

climate.ts.acf = Acf(climate.ts.diff, lag.max=365, 
                     main="Delhi Temperature Autocorrelation (min=1, max=365)")
climate.ts.acf


# Testing the predictability of the data
climate.ts.ar1 <- Arima(climate.ts, order = c(1,0,0))
summary(climate.ts.ar1)






#***********************************************Partitioning the data********************************************************
# Number of validation days. The last 114 days of the dataset represenets the number of days from 
# Jan 1 2017 to Apr 24 2017
validation <- 114
training <- length(climate.ts) - validation
train.ts <- window(climate.ts, start = 1, end = training)
valid.ts <- window(climate.ts, start = training + 1, end = training + validation)

head(train.ts)
head(valid.ts)

length(valid.ts)





#************************************************Building the Models*********************************************************

# Each of the following models wil predict 114 days. 114 days is the entire validation period, which is
# Jan 1 2017 to Apr 24 2017

#################### (1) Holt-Winter's (HW) exponential smoothing######################
# Create Holt-Winter's (HW) exponential smoothing for the training data set.
# Use ets() function with model = "ZZZ", i.e., automated selection 
# error, trend, and seasonality options.
# Use optimal alpha, beta, & gamma to fit HW over the training period.
hw.ZZZ <- ets(train.ts, model = "ZZZ")
summary(hw.ZZZ)

# Use forecast() function to make predictions using this HW model for the 
# validation period. 
# Show predictions in tabular format.
hw.ZZZ.pred <- forecast(hw.ZZZ, h = 114, level = 0)
hw.ZZZ.pred

# plotting the predictions plotted against the validation data
plot(hw.ZZZ.pred$mean, 
     xlab = "Time", ylab = "Temperature (in centigrade) ", ylim = c(0, 40),
     main = "Holt-Winter's Model - Train Data", col = "blue") 
lines(valid.ts, col = "black")
legend("topleft", legend = c("Holt-Winter's Forecast for Validation Period",
                             "Delhi Climate Daily Temperature Time Series"), 
       col = c("blue", "black"), lty = 2:1)




#Use forecast() with entire dataset
hw.entire.ZZZ <- ets(climate.ts, model ="ZZZ")
summary(hw.entire.ZZZ)
hw.entire.ZZZ.pred <- forecast(hw.entire.ZZZ, h = 7, level = 0)
hw.entire.ZZZ.pred

# Plot HW predictions for all data, optimal smoothing parameters.
plot(hw.entire.ZZZ.pred$mean, 
     xlab = "Time", ylab = "Temperature (in centigrade) ", ylim = c(0, 40),
     main = "Holt-Winter's Model - Entire Data Set", col = "blue") 
legend("topleft", legend = c("Holt-Winter's Forecast with Entire Dataset"), 
       col = c("blue"), lty =1)




########################## (2) Auto Arima Model ##################################
# Use auto.arima() function to fit ARIMA model.
# Use summary() to show auto ARIMA model and its parameters.
train.auto.arima <- auto.arima(train.ts) 
summary(train.auto.arima)

# Apply forecast() function to make predictions for ts with 
# auto ARIMA model.  
train.auto.arima.pred <- forecast(train.auto.arima, h = 114, level = 0)
train.auto.arima.pred

# plotting the predictions plotted against the validation data
plot(train.auto.arima.pred$mean, 
     xlab = "Time", ylab = "Temperature (in centigrade) ", ylim = c(0, 40),
     main = "Auto Arima Model - Train Data", col = "blue") 
lines(valid.ts, col = "black")
legend("topleft", legend = c("Auto Arima Forecast for Validation Period",
                             "Delhi Climate Daily Temperature Time Series"), 
       col = c("blue", "black"), lty = 2:1)



#Using all data set to test our entire dataset
# Use auto.arima() function to fit ARIMA model.
# Use summary() to show auto ARIMA model and its parameters.
entire.auto.arima <- auto.arima(climate.ts)
summary(entire.auto.arima)

# Apply forecast() function to make predictions for ts with 
# auto ARIMA model in validation set.  
entire.auto.arima.pred <- forecast(entire.auto.arima, h = 7, level = 0)
entire.auto.arima.pred


# Plot ts data, trend and seasonality data, and predictions for validation period.
plot(entire.auto.arima.pred$mean, 
     xlab = "Time", ylab = "Temperature (in centigrade) ", ylim = c(0, 40),
     main = "Auto Arima Model - Entire Data Set", col = "blue") 
legend("topleft", legend = c("Auto Arima Forecast with Entire Dataset"), 
       col = c("blue"), lty =1)



########################## (3) Arima Model with Seasonal ##################################
# building an arima model with seasonal parameters
seas.arima = Arima(train.ts, order = c(1,1,1), seasonal = c(1,1,1))
summary(seas.arima)

# forecasting using the arima(1,1,1)(1,1,1) model
seas.arima.pred = forecast(seas.arima, h=114, level=0)
seas.arima.pred

# plotting the predictions plotted against the validation data
plot(seas.arima.pred$mean, 
     xlab = "Time", ylab = "Temperature (in centigrade) ", ylim = c(0, 40),
     main = "ARIMA(1,1,1)(1,1,1) Model - Train Data", col = "blue") 
lines(valid.ts, col = "black")
legend("topleft", legend = c("ARIMA(1,1,1)(1,1,1) Forecast for Validation Period",
                         "Delhi Climate Daily Temperature Time Series"), 
       col = c("blue", "black"), lty = 2:1)



# building an arima(1,1,1)(1,1,1) model for the entire dataset
entire.seas.arima = Arima(climate.ts, order = c(1,1,1), seasonal = c(1,1,1))
summary(entire.seas.arima)

# using the arima(1,1,1)(1,1,1) model to forecast an entire year
entire.seas.arima.pred = forecast(entire.seas.arima, h=7, level=0)
entire.seas.arima.pred

# plotting the year long forecast using the arima(1,1,1)(1,1,1) trained on the entire dataset
plot(entire.seas.arima.pred$mean, 
     xlab = "Time", ylab = "Temperature (in centigrade) ", ylim = c(0, 40),
     main = "ARIMA(1,1,1)(1,1,1) Model - Entire Data Set", col = "blue") 
legend("topleft", legend = c("ARIMA(1,1,1)(1,1,1) Forecast with Entire Dataset"), 
       col = c("blue"), lty =1)



########################## (4) Quadratic TSLM Model ##################################
# building a TSLM quadratic model using the training data
TSLM.quad  <- tslm(train.ts ~ trend + I(trend^2))
summary(TSLM.quad)

# forecasting in the validation period
TSLM.quad.pred <- forecast(TSLM.quad , h=114, level=0)

# plotting the predictions plotted against the validation data
plot(TSLM.quad.pred$mean, 
     xlab = "Time", ylab = "Temperature (in centigrade) ", ylim = c(0, 40),
     main = "Quad TSLM Model - Training Data", col = "blue") 
lines(valid.ts, col = "black")
legend("topleft", legend = c("Quad TSLM Forecast for Validation Period",
                             "Delhi Climate Daily Temperature Time Series"), 
       col = c("blue", "black"), lty = 2:1)


# Checking the residuals of the quadratic LSTM model predictions
# Checking to see whether it is worth makinga two step model.
TSLM.quad.residual.acf <- Acf(TSLM.quad.pred$residuals, lag.max = 114, 
                                  main = "Autocorrelation for Delhi Climate Training Residuals")
TSLM.quad.residual.acf


# building an TSLM quadratic model using the entire dataset
TSLM.quad.entire <- tslm(climate.ts ~ trend + I(trend^2))
summary(TSLM.quad.entire)

TSLM.quad.entire.pred <- forecast(TSLM.quad.entire, h=7, level=0)

plot(TSLM.quad.entire.pred$mean, 
     xlab = "Time", ylab = "Temperature (in centigrade) ", ylim = c(0, 40),
     main = "Quad TSLM Model - Entire Data Set", col = "blue") 
legend("topleft", legend = c("Quad TSLM Forecast with Entire Dataset"), 
       col = c("blue"), lty =1)



########################## (5) Two Level Model ##################################
# building a two level quadratic LSTM model + ARIMA(1,0,0) of residual
TSLM.quad.pred.res.ar1 <- Arima(TSLM.quad.pred$residuals, order=c(1,0,0))
summary(TSLM.quad.pred.res.ar1)

# forecasting in the validation period
TSLM.quad.pred.res.ar1.pred <- forecast(TSLM.quad.pred.res.ar1, h=114, level=0)

# Combining the 2 level model
two_level <- TSLM.quad.pred$mean + TSLM.quad.pred.res.ar1.pred$mean

# plotting the predictions plotted against the validation data
plot(two_level, 
     xlab = "Time", ylab = "Temperature (in centigrade) ", ylim = c(0, 40),
     main = "Two Level Model - Training Data", col = "blue") 
lines(valid.ts, col = "black")
legend("topleft", legend = c("Two Level Model Forecast for Validation Period",
                             "Delhi Climate Daily Temperature Time Series"), 
       col = c("blue", "black"), lty = 2:1)




# building a two level model using the entire dataset
TSLM.quad.pred.res.ar1.entire <- Arima(TSLM.quad.entire.pred$residuals, order=c(1,0,0))
summary(TSLM.quad.pred.res.ar1.entire)

TSLM.quad.pred.res.ar1.entire.pred <- forecast(TSLM.quad.pred.res.ar1.entire, h=7, level=0)

# combining the two level model
two_level.entire <- TSLM.quad.entire.pred$mean + TSLM.quad.pred.res.ar1.entire.pred$mean

plot(two_level.entire, 
     xlab = "Time", ylab = "Temperature (in centigrade) ", ylim = c(0, 40),
     main = "Two Level Model - Entire Data Set", col = "blue") 
legend("topleft", legend = c("Two Level Forecast with Entire Dataset"), 
       col = c("blue"), lty =1)


#calculating the fitted values for the two level model using the entire dataset
two_level.entire.fitted <- TSLM.quad.entire.pred$fitted + TSLM.quad.pred.res.ar1.entire.pred$fitted


############################## (6) Naive Model ###################################
naive.temp <- naive(train.ts, h=114)   
naive.temp

naive.entire.temp <- naive(climate.ts, h=7)   
naive.entire.temp

#****************************************Accuracy Measures**************************************************

#
#Evaluate the model forecast accuracy when compared to the validation period of 114 days
#HW model accuracy
hw.acc <- round(accuracy(hw.ZZZ.pred$mean, valid.ts), 3)
#auto arima model accuracy
autoarima.acc <- round(accuracy(train.auto.arima.pred$mean, valid.ts),3)
#arima(1,1,1)(1,1,1) model accuracy
arima.acc <- round(accuracy(seas.arima.pred$mean, valid.ts),3)
#lstm quadratic model accuracy
quad.acc <- round(accuracy(TSLM.quad.pred$mean, valid.ts),3)
#two level model accuracy
two_level.acc <- round(accuracy(two_level, valid.ts), 3)
# naive model accuracy
naive.acc <- round(accuracy(naive.temp$mean, valid.ts),3)

# creating the table
Models <- c("HW Model","Auto Arima Model", "Arima(1,1,1)(1,1,1)","Quad TSLM","Two Level","Naive")
RMSE <- c(hw.acc[2], autoarima.acc[2], arima.acc[2], 
          quad.acc[2], two_level.acc[2], naive.acc[2])
MAPE <- c(hw.acc[5], autoarima.acc[5], arima.acc[5], 
          quad.acc[5], two_level.acc[5], naive.acc[5])

acc_table <- data.frame(Models, RMSE, MAPE)

acc_table







# comparing the common accuracy measures for the models by using the fitted values
# of each forecasts compared against the entire climate dataset time series
# measuring the common accuracy for HW model
hw.entire.acc <- round(accuracy(hw.entire.ZZZ.pred$fitted, climate.ts),3)
# measuring the common accuracy for auto arima model
autoarima.entire.acc <- round(accuracy(entire.auto.arima.pred$fitted, climate.ts),3)
# measuring the common accuracy for arima(1,1,1)(1,1,1) model
arima.entire.acc <- round(accuracy(entire.seas.arima.pred$fitted, climate.ts),3)
# measuring the common accuracy for the quadratic TSLM model
quad.entire.acc <- round(accuracy(TSLM.quad.entire.pred$fitted, climate.ts),3)
# measuring the common accuracy for the two level model
two_level.entire.acc <- round(accuracy(two_level.entire.fitted, climate.ts), 3)
# measuring the common accuracy for the naive model
naive.entire.acc <- round(accuracy(naive.entire.temp$fitted, climate.ts),3)

# creating the table
Models.entire <- c("HW Model","Auto Arima Model", "Arima(1,1,1)(1,1,1)","Quad TSLM","Two Level","Naive")
RMSE.entire <- c(hw.entire.acc[2], autoarima.entire.acc[2], arima.entire.acc[2], 
          quad.entire.acc[2], two_level.entire.acc[2], naive.entire.acc[2])
MAPE.entire <- c(hw.entire.acc[5], autoarima.entire.acc[5], arima.entire.acc[5], 
          quad.entire.acc[5], two_level.entire.acc[5], naive.entire.acc[5])

acc_table.entire <- data.frame(Models.entire, RMSE.entire, MAPE.entire)

acc_table.entire


