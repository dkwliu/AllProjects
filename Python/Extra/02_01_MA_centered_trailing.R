
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

## CREATE CENTERED MA FOR VARIOUS WINDOWS (NUMBER OF PERIODS).

# Create centered moving average with window k = 4, 5, and 12.
ma.centered_4 <- ma(ridership.ts, order = 4)
ma.centered_5 <- ma(ridership.ts, order = 5)
ma.centered_12 <- ma(ridership.ts, order = 12)


## CREATE TRAILING MA FOR VARIOUS WINDOWS (NUMBER OF PERIODS).
## SHOW FIRST SIX AND LAST SIX VALUES OF TRAILING MA.
## COMBINE ORIGINAL DATA AND TRAILING MA IN TABLE.
## IDENTIFY FORECAST ACCURACY FOR TRAILING MA FORECASTS.
## FORECAST USING TRAILING MA.

# Create trailing moving average with window (number of periods) k = 4, 5, and 12.
# In rollmean(), use argument align = "right" to calculate a trailing MA.
ma.trailing_4 <- rollmean(ridership.ts, k = 4, align = "right")
ma.trailing_5 <- rollmean(ridership.ts, k = 5, align = "right")
ma.trailing_12 <- rollmean(ridership.ts, k = 12, align = "right")

# Use head() function to show the first 6 MA results 
# and tail() function to show the last 6 MA results.
head(ma.trailing_12)
tail(ma.trailing_12)

# Combine ridership.ts and ma.trailing in one data table.
# The data is unequal in length (159 data points in ridership.ts
# vs. 148 data points in ma.trailing). Thus, it is required 
# to add NA (not applicalbe) to the first 11 periods before 
# the first trailing ma in period 12 (Dec 1991). 

ma.trail_4 <- c(rep(NA, length(ridership.ts) - length(ma.trailing_4)), ma.trailing_4)
ma.trail_5 <- c(rep(NA, length(ridership.ts) - length(ma.trailing_5)), ma.trailing_5)
ma.trail_12 <- c(rep(NA, length(ridership.ts) - length(ma.trailing_12)), ma.trailing_12)

ma_trailing_tab <- cbind(ridership.ts, ma.trail_4, ma.trail_5, ma.trail_12)
ma_trailing_tab

# Use accuracy() function to identify common accuracy measures.
# Use round() function to round accuracy measures to three decimal digits.
round(accuracy(ma.trail_4, ridership.ts), 3)
round(accuracy(ma.trail_5, ridership.ts), 3)
round(accuracy(ma.trail_12, ridership.ts), 3)


## Create trailing MA forecast for 12 periods into the future.
ma.trailing_12.pred <- forecast(ma.trailing_12, h=12, level = 0)
ma.trailing_12.pred

## GENERATE PLOT FOR ORIGINAL DATA, CENTERED AND TRAILING MA.

# Plot original data and centered MA.
plot(ridership.ts, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2200), bty = "l",
     xlim = c(1991, 2006.25), main = "Cantered Moving Average") 
axis(1, at = seq(1991, 2004.25, 1), labels = format(seq(1991, 2004.25, 1)))
lines(ma.centered_4, col = "brown", lwd = 2)
lines(ma.centered_12, col = "blue", lwd = 2)
legend(1992,2200, legend = c("Ridership", "Centered MA, k=4",
                             "Centered MA, k=12"), 
                  col = c("black", "brown" , "blue"), 
                  lty = c(1, 1, 1), lwd =c(1, 2, 2), bty = "n")

# Plot original data and trailing MA.
plot(ridership.ts, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2200), bty = "l",
     xlim = c(1991, 2006.25), main = "Trailing Moving Average") 
axis(1, at = seq(1991, 2004.25, 1), labels = format(seq(1991, 2004.25, 1)))
lines(ma.trailing_4, col = "brown", lwd = 2, lty = 1)
lines(ma.trailing_12, col = "blue", lwd = 2, lty = 5)
legend(1992,2200, legend = c("Ridership", "Trailing MA, k=4", 
                             "Trailing MA, k=12"), 
                  col = c("black", "brown", "blue"), 
                  lty = c(1, 1, 5), lwd =c(1, 2, 2), bty = "n")


# Plot original data, centered and trailing MA.
plot(ridership.ts, 
     xlab = "Time", ylab = "Ridership (in 000s)", ylim = c(1300, 2200), bty = "l",
     xlim = c(1991, 2006.25), main = "Cantered and Trailing Moving Averages") 
axis(1, at = seq(1991, 2004.25, 1), labels = format(seq(1991, 2004.25, 1)))
lines(ma.centered_12, col = "brown", lwd = 2)
lines(ma.trailing_12, col = "blue", lwd = 2, lty = 5)
legend(1992,2200, legend = c("Ridership", "Centered MA, k=12",
                             "Trailing MA, k=12"), 
                  col = c("black", "brown", "blue"), 
                  lty = c(1, 1, 5), lwd =c(1, 2, 2), bty = "n")






