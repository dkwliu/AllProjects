
## LOAD LIBRARIES AND INITIAL DATA INPUT

# Use forecast and zoo libraries
library(forecast)


# Create time series data in R.
# set working directory for locating files.
setwd("C:/misc/673_BAN/module2_smoothing")

# create data frame.
Amtrak.data <- read.csv("Amtrak.csv")

# See the first 6 records of the file.
head(Amtrak.data)

# Create time series data set using ts() function. 
# Takes three arguments: start, end, and freq.
# With monthly data, the frequency of periods per season is 12 per year. 
# Arguments start and end are pairs (season number, period number).
ridership.ts <- ts(Amtrak.data$Ridership, 
                   start = c(1991, 1), end = c(2004, 3), freq = 12)


## SIMPLE EXPONENTIAL SMOOTHING (SES) WITH PARTITIONED DATA, ALPHA = 0.2.

# Create fixed data partitioning for data.
# Define the numbers of months in the training and validation sets,
# nTrain and nValid, respectively.
nValid <- 36
nTrain <- length(ridership.ts) - nValid
train.ts <- window(ridership.ts, start = c(1991, 1), end = c(1991, nTrain))
valid.ts <- window(ridership.ts, start = c(1991, nTrain + 1), 
                   end = c(1991, nTrain + nValid))

# Create simple exponential smoothing (SES) for Amtrak data.
# Use ets() function with model = "ANN", i.e., additive error(A), no trend (N),
# & no seasonality (N). Use alpha = 0.2 to fit SES over the training period.
ses.orig <- ets(train.ts, model = "ANN", alpha = 0.2)
ses.orig

# Use forecast() function to make predictions using this SES model 
# validation period (nValid). 
# Show predictions in tabular format
ses.orig.pred <- forecast(ses.orig, h = nValid, level = 0)
ses.orig.pred


## HOLT'S EXPONENTIAL SMOOTHING WITH PARTITIONED DATA

# Use ets() function with model = "AAN", i.e., additive error(A), 
# additive trend (A), & no seasonality (N). 
h.AAN <- ets(train.ts, model = "AAN", alpha = 0.1, beta = 0.1)
h.AAN

# Use forecast() function to make predictions using this HW model for 
# validation period (nValid). 
# Show predictions in tabular format.
h.AAN.pred <- forecast(h.AAN, h = nValid, level = 0)
h.AAN.pred


# Holt's model with optimal smoothing parameters.
# Use ets() function with model = "AAN", i.e., additive error(A), 
# additive trend (A), & no seasonality (N). 
h.AAN.opt <- ets(train.ts, model = "AAN")
h.AAN.opt

# Use forecast() function to make predictions using this HW model for 
# validation period (nValid). 
# Show predictions in tabular format.
h.AAN.opt.pred <- forecast(h.AAN.opt, h = nValid, level = 0)
h.AAN.opt.pred


## HOLT-WINTER'S (HW) EXPONENTIAL SMOOTHING WITH PARTITIONED DATA, 
## OPTIMAL PARAMETERS FOR ALPHA, BETA, AND GAMMA.

# Create Holt-Winter's (HW) exponential smoothing for partitioned data.
# Use ets() function with model = "AAA", i.e., additive error(A), 
# additive trend (A), & additive seasonality (A). 
# Use optimal alpha, beta, & gamma to fit HW over the training period.
hw.AAA <- ets(train.ts, model = "AAA")
hw.AAA

# Use forecast() function to make predictions using this HW model for 
# validation period (nValid). 
# Show predictions in tabular format.
hw.AAA.pred <- forecast(hw.AAA, h = nValid, level = 0)
hw.AAA.pred

# Plot HW predictions for original data, optimal smoothing parameters.
plot(hw.AAA.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), 
     main = "Holt-Winter's Additive Model with Optimal Smoothing Parameters", flty = 2) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(hw.AAA.pred$fitted, col = "blue", lwd = 2)
lines(valid.ts)

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


## HOLT-WINTER'S (HW) EXPONENTIAL SMOOTHING WITH PARTITIONED DATA, AUTOMATED
## ERROR, TREND and SEASONALITY (ZZZ) OPTIONS, AND OPTIMAL PARAMETERS
## ALPHA, BETA, AND GAMMA.

# Create Holt-Winter's (HW) exponential smoothing for partitioned data.
# Use ets() function with model = "ZZZ", i.e., automated selection 
# error, trend, and seasonality options.
# Use optimal alpha, beta, & gamma to fit HW over the training period.
hw.ZZZ <- ets(train.ts, model = "ZZZ")
hw.ZZZ # Model appears to be (M, N, A), with alpha = 0.555 and gamma = 0.0001.

# Use forecast() function to make predictions using this HW model with 
# validation period (nValid). 
# Show predictions in tabular format.
hw.ZZZ.pred <- forecast(hw.ZZZ, h = nValid, level = 0)
hw.ZZZ.pred

# Plot HW predictions for original data, optimal smoothing parameters.
plot(hw.ZZZ.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), 
     main = "Holt-Winter's Model with Automated Selection of Model Options", flty = 2) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(hw.ZZZ.pred$fitted, col = "blue", lwd = 2)
lines(valid.ts)

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


## COMPARE ACCURACY OF THREE MODELS: SES WITH ALPHA = 0.2, HW
## ADDITIVE MODEL WITH OPTIMAL PARAMETERS, AND HW MODEL WITH
## AUTOMATED SELECTION OF MODEL OPTIONS.
round(accuracy(ses.orig.pred, valid.ts), 3)
round(accuracy(hw.AAA.pred, valid.ts), 3)
round(accuracy(hw.ZZZ.pred, valid.ts), 3)


## FORECAST WITH HOLT-WINTER'S MODEL USING ENTIRE DATA SET INTO
## THE FUTURE FOR 12 PERIODS.

# Create Holt-Winter's (HW) exponential smoothing for full Amtrak data set. 
# Use ets() function with model = "ZZZ", to identify the best HW option
# and optimal alpha, beta, & gamma to fit HW for the entire data period.
HW.ZZZ <- ets(ridership.ts, model = "ZZZ")
HW.ZZZ # Model appears to be (A, N, A), with alpha = 0.5558 and gamma = 0.0003.

# Use forecast() function to make predictions using this HW model for
# 12 month into the future.
length <- length(ridership.ts)
HW.ZZZ.pred <- forecast(HW.ZZZ, h = 12 , level = c(0, 95))
HW.ZZZ.pred
HW.ZZZ.pred$fitted

# plot HW predictions for original data, optimal smoothing parameters.
plot(HW.ZZZ.pred, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2600), bty = "l",
     xaxt = "n", xlim = c(1991, 2006.25), lwd = 2,
     main = "Holt-Winter's Model with Automated Selection of Model Options and Forecast for Future Periods", 
     flty = 5) 
axis(1, at = seq(1991, 2006, 1), labels = format(seq(1991, 2006, 1)))
lines(HW.ZZZ.pred$fitted, col = "blue", lwd = 2)


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

# Identify performance measures for HW forecast.
round(accuracy(HW.ZZZ.pred$fitted, ridership.ts), 3)
round(accuracy((naive(ridership.ts))$fitted, ridership.ts), 3)
round(accuracy((snaive(ridership.ts))$fitted, ridership.ts), 3)
