#Case Study #2: Forecasting Walmart’s Revenue
#The data set for case study #2 represents quarterly revenues (in $million) in Walmartfrom the first quarter
#of 2005 through the second quarter of 2020 (673_case2.csv). This quarterly data is collected from
#www.macrotrends.net/stocks/charts/WMT/walmart/revenue. The goal is to forecast Walmart’s quarterly
#revenue for the next two quarters of 2020 and the first two quarters of 2021. 

library(forecast)
library(zoo)

#QUESTION 1. Plot the data and visualize time series components.

#1a. Create time series data set in R using the ts() function.
walmart_rev.data <- read.csv("case2.csv")
walmart_rev.ts <- ts(walmart_rev.data$Revenue, start=c(5,1), end=c(20,2), freq=4) 
walmart_rev.ts

#1b. Apply the plot() function to create a data plot with the historical data, provide it in your
#report, and explain what time series components can be visualized in this plot. A
plot(walmart_rev.ts, xlab="Year", ylab="Sales (millions $)", ylim=c(0,150000), main="Walmart Revenue")






#QUESTION 2. Apply five regression models using data partition.
#Consider the following 5 regression-based models:
#  i. Regression model with linear trend
#  ii. Regression mode with quadratic trend
#  iii. Regression model with seasonality
#  iv. Regression model with linear trend and seasonality
#  v. Regression model with quadratic trend and seasonality.
#2a. Develop data partition with the validation partition of 16 periods and the rest for the
#training partition.
validation <- 16
training <- length(walmart_rev.ts) - validation
walmart_rev_train.ts <- window(walmart_rev.ts, start = c(5,1), end = c(5, training))
walmart_rev_valid.ts <- window(walmart_rev.ts, start = c(5, training + 1), 
                               end = c(5, training + validation))

walmart_rev_train.ts
walmart_rev_valid.ts

#2b.	Use the tslm() function for the training partition to develop each of the 5 regression models 
#from the above list. Apply the summary() function to identify the model structure and parameters 
#for each regression model, show them in your report, and also present the respective model equation. 
# Use each model to forecast revenues for the validation period using the forecast() function. 

#  i. Regression model with linear trend
walmart_rev.reg.lin <- tslm(walmart_rev_train.ts ~ trend)
summary(walmart_rev.reg.lin)

walmart_rev.reg.lin.pred <- forecast(walmart_rev.reg.lin, h = 16, level = 0)
walmart_rev.reg.lin.pred

#  ii. Regression mode with quadratic trend
walmart_rev.reg.quad <- tslm(walmart_rev_train.ts ~ trend + I(trend^2))
summary(walmart_rev.reg.quad)

walmart_rev.reg.quad.pred <- forecast(walmart_rev.reg.quad, h = 16, level = 0)
walmart_rev.reg.quad.pred 

#  iii. Regression model with seasonality
walmart_rev.reg.seas <- tslm(walmart_rev_train.ts ~ season)
summary(walmart_rev.reg.seas)

walmart_rev.reg.seas.pred <- forecast(walmart_rev.reg.seas, h = 16, level = 0)
walmart_rev.reg.seas.pred 

#  iv. Regression model with linear trend and seasonality
walmart_rev.reg.lin.seas <- tslm(walmart_rev_train.ts ~ trend + season)
summary(walmart_rev.reg.lin.seas)

walmart_rev.reg.lin.seas.pred <- forecast(walmart_rev.reg.lin.seas, h = 16, level = 0)
walmart_rev.reg.lin.seas.pred 

#  v. Regression model with quadratic trend and seasonality.
walmart_rev.reg.quad.seas <- tslm(walmart_rev_train.ts ~ trend + I(trend^2) + season)
summary(walmart_rev.reg.quad.seas)

walmart_rev.reg.quad.seas.pred <- forecast(walmart_rev.reg.quad.seas, h = 16, level = 0)
walmart_rev.reg.quad.seas.pred 

#2c. Apply the accuracy() function to compare performance measure of the 5 forecasts you
#developed in 2b. Present the accuracy measures in your report, compare them, and, using
#MAPE and RMSE, identify the two most accurate regression models for forecasting.
reg.lin.measures <- accuracy(walmart_rev.reg.lin.pred, walmart_rev_valid.ts)
reg.quad.measures <- accuracy(walmart_rev.reg.quad.pred, walmart_rev_valid.ts)
reg.seas.measures <- accuracy(walmart_rev.reg.seas.pred, walmart_rev_valid.ts)
reg.lin.seas.measures <- accuracy(walmart_rev.reg.lin.seas.pred, walmart_rev_valid.ts)
reg.quad.seas.measures <- accuracy(walmart_rev.reg.quad.seas.pred, walmart_rev_valid.ts)

models <- c("Linear Trend","Quadratic Trend","Seasonality","Linear Trend with Seasonality",
            "Quadratic Trend with Seasonality")
RMSE <- c(reg.lin.measures[4], reg.quad.measures[4], reg.seas.measures[4], 
          reg.lin.seas.measures[4], reg.quad.seas.measures[4])
MAPE <- c(reg.lin.measures[10], reg.quad.measures[10], reg.seas.measures[10], 
          reg.lin.seas.measures[10], reg.quad.seas.measures[10])
accuracy_table <- data.frame(models, RMSE, MAPE)

accuracy_table





#QUESTION 3. Employ the entire data set to make time series forecast. 

#3a.	Apply the two most accurate regression models identified in question to make the forecast 
#for the last two quarters of 2020 and first two quarters of 2021. For that, use the entire data 
#set to develop the regression model using the tslm() function. Apply the summary() function to 
#identify the model structure and parameters, show them in your report, and also present the 
#respective model equation. Use each model to forecast Walmart’s revenue in the 4 quarters of 
#2020 and 2021 using the forecast() function, and present this forecast in your report. 

#Regression model with linear trend and seasonality using all data
walmart_rev.reg.lin.seas.all <- tslm(walmart_rev.ts ~ trend + season)
summary(walmart_rev.reg.lin.seas.all)

walmart_rev.reg.lin.seas.all.pred <- forecast(walmart_rev.reg.lin.seas.all, h=4, level=0)
walmart_rev.reg.lin.seas.all.pred

#Regression model with quadratic trend and seasonality using all data
walmart_rev.reg.quad.seas.all <- tslm(walmart_rev.ts ~ trend + I(trend^2) + season)
summary(walmart_rev.reg.quad.seas.all)

walmart_rev.reg.quad.seas.all.pred <- forecast(walmart_rev.reg.quad.seas.all, h=4, level=0)
walmart_rev.reg.quad.seas.all.pred

#3b.	Apply the accuracy() function to compare the performance measures of the regression models 
#developed in 3a with those for naïve and seasonal naïve forecasts. Present the accuracy measures 
#in your report, compare them, and identify, using MAPE and RMSE, which forecast is most accurate 
#to forecast Walmart’s quarterly revenue in 2020 and 2021.

#naive forecast
walmart_rev.naive <- naive(walmart_rev.ts)

#seasonal naive forecast
walmart_rev.snaive <- snaive(walmart_rev.ts)

# accuracy measures between regression model with linear trend and seasonality, regression model
# with quadratic trend and seasonality, naive model, and seasonal naive model. All models uses
# entire walmart revenue data.
linear.seas.measures <- accuracy(walmart_rev.reg.lin.seas.all.pred$fitted, walmart_rev.ts)
quad.seas.measures <- accuracy(walmart_rev.reg.quad.seas.all.pred$fitted, walmart_rev.ts)
naive.measures <- accuracy(walmart_rev.naive$fitted, walmart_rev.ts)
snaive.measures <- accuracy(walmart_rev.snaive$fitted, walmart_rev.ts)

linear.seas.measures
quad.seas.measures
naive.measures
snaive.measures

all_data.models <- c("Linear with seasonality","Quadratic with seasonality","Naive","Seasonal naive")
all_data.RMSE <- c(linear.seas.measures[2], quad.seas.measures[2], naive.measures[2], snaive.measures[2])
all_data.MAPE <- c(linear.seas.measures[5], quad.seas.measures[5], naive.measures[5], snaive.measures[5])
all_data.accuracy_table <- data.frame(all_data.models, all_data.RMSE, all_data.MAPE)

all_data.accuracy_table