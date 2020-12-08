library(forecast)

#Case Study #3: Forecasting with AR and ARIMA Models
#Consider the quarterly data on Walmart revenues (in $million) from the first quarter of 2005 through the 
#second quarter of 2020 (673_case2.csv). The goal is to forecast Walmart’s quarterly revenue for the next 
#two quarters of 2020 and the first two quarters of 2021. 

#As you did in case study #2, start this case with the following: 
#•	Create time series data set in R using the ts() function. 
walmart.data <- read.csv("case2.csv")
walmart.ts <- ts(walmart.rev.data$Revenue, start=c(2005,1), end=c(2020,2), freq=4)
walmart.ts

#•	Develop data partition with the validation partition of 16 periods and the rest for the training partition. 
validation <- 16
training <- length(walmart.ts) - validation
walmart.train.ts <- window(walmart.ts, start = c(2005,1), end = c(2005, training))
walmart.valid.ts <- window(walmart.ts, start = c(2005, training + 1), end = c(2005, training + validation))

walmart.train.ts
walmart.valid.ts

#Questions 
#1a. Identify time series predictability. 
#    Using the AR(1) model for the historical data, Provide and explain the AR(1) model summary in your report. 
#    Explain if the Walmart revenue is predictable. 
walmart.ar1 <- Arima(walmart.ts, order = c(1,0,0))
summary(walmart.ar1)

#1b. Using the first differencing (lag-1) of the historical data and Acf() function Provide in the report the 
#    autocorrelation plot of the first differencing (lag-1) with the maximum of 8 lags and explain if Walmart 
#    revenue is predictable. 
diff.walmart <- diff(walmart.ts, lag = 1)
diff.walmart

diff.walmart.acf <- Acf(diff.walmart, lag.max = 8, 
                        main = "Autocorrelation for Differenced Walmart Revenue")
diff.walmart.acf

#2.	 Apply the two-level forecast with regression model and AR model for residuals. 
#2a. For the training data set, use the tslm() function to develop a regression model with quadratic trend and 
#    seasonality. Forecast Walmart’s revenue with the forecast() function (use the associated R code from case #2). 
#    No explanation is required in your report. 
walmart.reg.quad.seas <- tslm(walmart.train.ts ~ trend + I(trend^2) + season)

walmart.reg.quad.seas.pred <- forecast(walmart.reg.quad.seas, h = 16, level = 0)
walmart.reg.quad.seas.pred 

#2b. Identify the regression model’sresiduals for the training period and use the Acf() function with the maximum 
#    of 8 lags to identify autocorrelation for these residuals. Provide the autocorrelation plot in your report and 
#    explain if it would be a good idea to add to your forecast an AR model for residuals. 
walmart.train.residual <- walmart.reg.quad.seas.pred$residuals

walmart.train.residual.acf <- Acf(walmart.train.residual, lag.max = 8, 
                                  main = "Autocorrelation for Walmart Revenue Training Residuals")

#2c. Develop an AR(1) model for the regression residuals, present and explain the model and its equation in your 
#    report. Use the Acf() function for the residuals of the AR(1) model (residuals of residuals), present the 
#    autocorrelation chart, and explain it in your report.
walmart.train.residual.ar1 <- Arima(walmart.train.residual, order = c(1,0,0))
summary(walmart.train.residual.ar1)

Acf(walmart.train.residual.ar1$residuals, lag.max = 8, 
    main = "Autocorrelation for the Residuals of the AR1 Model")

#2d. Create a two-level forecasting model (regression model with quadratic trend and seasonality + AR(1) model for 
#    residuals) for the validation period. Show in your report a table with the validation data, regression forecast 
#    for the validation data, AR() forecast for the validation data, and combined forecast for the validation period. 
walmart.train.residual.ar1.pred <- forecast(walmart.train.residual.ar1, h = 16, level = 0)
walmart.train.residual.ar1.pred

walmart.2level <- walmart.reg.quad.seas.pred$mean + walmart.train.residual.ar1.pred$mean
walmart.2level

walmart.train.ar1 <- Arima(walmart.train.ts, order = c(1,0,0))
walmart.train.ar1.pred <- forecast(walmart.train.ar1, h=16, level = 0)
walmart.train.ar1.pred

val.table <- data.frame(walmart.valid.ts, walmart.reg.quad.seas.pred$mean, walmart.train.residual.ar1.pred$mean,
                        walmart.train.ar1.pred$mean, walmart.2level)
names(val.table) <- c("Walmart Val", "TSLM Forecast", "AR(1) Residual Forecast", "AR(1) Forecast", "Two Level")

val.table

#2e. Develop a two-level forecast (regression model with quadratic trend and seasonality and AR(1) model for residuals) 
#    for the entire data set. Provide in your report the autocorrelation chart for the AR(1) model’s residuals and 
#    explain it. Also, provide a data table with the models’ forecasts for Walmart revenue in 2020-2021 
#    (regression model, AR(1) for residuals, and two-level combined forecast)

walmart.reg.quad.seas.alldata <- tslm(walmart.ts ~ trend + I(trend^2) + season)
walmart.reg.quad.seas.alldata.pred <- forecast(walmart.reg.quad.seas.alldata, h = 6, level = 0)
walmart.reg.quad.seas.alldata.pred

walmart.residual.alldata <- walmart.reg.quad.seas.alldata.pred$residuals
walmart.residual.alldata.ar1 <- Arima(walmart.residual.alldata, order=c(1,0,0))
walmart.residual.alldata.ar1.pred <- forecast(walmart.residual.alldata.ar1, h=6, level=0)
walmart.residual.alldata.ar1.acf <- Acf(walmart.residual.alldata.ar1.pred$residuals, lag.max = 8, 
                                        main = "Autocorrelation for the Residuals of the AR1 Model with All Data")
walmart.residual.alldata.ar1.acf

walmart.2level.alldata <- walmart.reg.quad.seas.alldata.pred$mean + walmart.residual.alldata.ar1.pred$mean
walmart.2level.alldata         

val.table.alldata <- data.frame(walmart.reg.quad.seas.alldata.pred$mean, 
                                walmart.residual.alldata.ar1.pred$mean, walmart.2level.alldata )
names(val.table.alldata) <- c("TSLM Forecast All", "AR(1) Residual Forecast All", "Two Level All")

val.table.alldata
 


# 3.	Use ARIMA Model and Compare Various Methods. 
# 3a.	Use Arima() function to fit ARIMA(1,1,1)(1,1,1) model for the training data set. Insert in your report 
#     the summary of this ARIMA model, present and briefly explain the ARIMA model and its equation in your 
#     report. Using this model, forecast revenue for the validation period and present it in your report. 
walmart.ar.seas <- Arima(walmart.train.ts , order = c(1,1,1), seasonal = c(1,1,1)) 
summary(walmart.ar.seas)

walmart.ar.seas.pred <- forecast(walmart.ar.seas, h=16, level=0)
walmart.ar.seas.pred

# 3b.	Use the auto.arima() function to develop an ARIMA model using the training data set. Insert in your 
#     report the summary of this ARIMA model, present and explain the ARIMA model and its equation in your 
#     report. Use this model to forecast revenue in the validation period and present this forecast in your 
#     report. 
walmart.ar.seas.auto <- auto.arima(walmart.train.ts)
summary(walmart.ar.seas.auto)

walmart.ar.seas.auto.pred <- forecast(walmart.ar.seas.auto, h=16, level=0)
walmart.ar.seas.auto.pred

# 3c.	Apply the accuracy() function to compare performance measures of the two ARIMA models in 3a and 3b. 
#     Present the accuracy measures in your report, compare them and identify, using MAPE and RMSE, the best 
#     ARIMA model to apply. 
walmart.ar.seas.acc <- accuracy(walmart.ar.seas.pred, walmart.valid.ts)
walmart.ar.seas.acc

walmart.ar.seas.auto.acc <- accuracy(walmart.ar.seas.auto.pred, walmart.valid.ts)
walmart.ar.seas.auto.acc

labels <- c("RMSE", "MAPE")
AR_Model <- c(walmart.ar.seas.acc[4], walmart.ar.seas.acc[10]) 
auto_AR_Model <- c(walmart.ar.seas.auto.acc[4], walmart.ar.seas.auto.acc[10])
walmart.acc.table <- data.frame(labels, AR_Model, auto_AR_Model )
walmart.acc.table

# 3d.	Use two ARIMA models from 3a and 3b for the entire data set. Present models’ summaries in your report. 
#     Use these ARIMA modelsto forecast Walmart revenue in 2020- 2021 and present these forecasts in your report. 
walmart.ar.seas.alldata <- Arima(walmart.ts , order = c(1,1,1), seasonal = c(1,1,1)) 
summary(walmart.ar.seas.alldata)

walmart.ar.seas.alldata.pred <- forecast(walmart.ar.seas.alldata, h=6, level=0)
walmart.ar.seas.alldata.pred


walmart.ar.seas.auto.alldata <- auto.arima(walmart.ts)
summary(walmart.ar.seas.auto.alldata)

walmart.ar.seas.auto.alldata.pred <- forecast(walmart.ar.seas.auto.alldata, h=6, level=0)
walmart.ar.seas.auto.alldata.pred

model_pred <- data.frame(walmart.ar.seas.alldata.pred$mean, walmart.ar.seas.auto.alldata.pred$mean)
model_pred


# 3e.	Apply the accuracy() function to compare performance measures of the following forecasting models for 
#     the entire data set: (1) regression model with quadratic trend and seasonality; (2) two-level model 
#     (with AR(1) model for residuals); (3) ARIMA(1,1,1)(1,1,1) model; (4) auto ARIMA model; and (5) seasonal 
#     naïve forecast for the entire data set. Present the accuracy measures in your report, compare them, and 
#     identify, using MAPE and RMSE, the best model to use for forecasting Walmart’s revenue in quarters 3 and 4 
#     of 2020 and quarters 1 and 2 of 2021.

# TSLM model
walmart.alldata.tslm.pred <- forecast(walmart.reg.quad.seas.alldata, h = 4, level = 0)
walmart.alldata.tslm.pred

# Residual model
walmart.alldata.tslm.pred.res <- walmart.alldata.tslm.pred$residuals
walmart.alldata.res.ar1 <- Arima(walmart.alldata.tslm.pred.res, order=c(1,0,0))
walmart.alldata.res.ar1.pred <- forecast(walmart.alldata.res.ar1, h=4, level=0)
walmart.alldata.res.ar1.pred

# Two level model
walmart.alldata.2level.pred <- walmart.alldata.tslm.pred$mean + walmart.alldata.res.ar1.pred$mean
walmart.alldata.2level.pred
walmart.alldata.2level.fitted <- walmart.alldata.tslm.pred$fitted + walmart.alldata.res.ar1.pred$fitted


# ARIMA(1,1,1)(1,1,1) model
walmart.ar.seas.alldata.pred <- forecast(walmart.ar.seas.alldata, h=4, level=0)
walmart.ar.seas.alldata.pred

# Auto ARIMA model
walmart.ar.seas.auto.alldata.pred <- forecast(walmart.ar.seas.auto.alldata, h=4, level = 0)
walmart.ar.seas.auto.alldata.pred

# Seasonal Naive model
walmart.snaive.pred <- snaive(walmart.ts, h=4)   
walmart.snaive.pred

#Accuracy
TSLM.acc <- accuracy(walmart.alldata.tslm.pred$fitted, walmart.ts)
twolevel.acc <- accuracy(walmart.alldata.2level.fitted, walmart.ts)
ARIMA.acc <- accuracy(walmart.ar.seas.alldata.pred$fitted, walmart.ts)
auto.ARIMA.acc <- accuracy(walmart.ar.seas.auto.alldata.pred$fitted, walmart.ts)
snaive.acc <- accuracy(walmart.snaive.pred$fitted, walmart.ts)


TSLM <- c(TSLM.acc[2], TSLM.acc[5])
TwoLevel <- c(twolevel.acc[2], twolevel.acc[5])
ARIMA <- c(ARIMA.acc[2], ARIMA.acc[5])
auto.ARIMA <- c(auto.ARIMA.acc[2], auto.ARIMA.acc[5])
seasonal_naive <- c(snaive.acc[2], snaive.acc[5])

labels <- c("RMSE","MAPE")

alldata.table <- data.frame(labels, TSLM, TwoLevel, ARIMA, auto.ARIMA, seasonal_naive)
alldata.table
