
## USE FORECAST LIBRARY.

# install.packages("forecast")
library(forecast)

## CREATE DATA FRAME FOR S&P500 STOCK PRICES AND AMTRAK RIDERSHIP. 

# Set working directory for locating files.
setwd("C:/misc/673_BAN/module4_arima")

# Create data frame.
SP500.data <- read.csv("S&P500prices_19.csv")
Amtrak.data <- read.csv("Amtrak.csv")

# See the first 6 records of the file for S&P500 and Amtrak Ridership.
head(SP500.data)
tail(SP500.data)
head(Amtrak.data)



## USE ts() FUNCTION TO CREATE TIME SERIES DATASET FOR S&P500 CLOSE STOCK PRICES.

# Function ts() takes three arguments: start, end, and freq.
# With monthly data, frequency (freq) of periods in a season (year) is 12. 
# With quarterly data, frequency in a season (year) is equal to 4.
# Arguments start and end are pairs: (season number, period number).
close.price.ts <- ts(SP500.data$ClosePrice,
                     start = 1, freq = 1)
close.price.ts

# Use plot() function to create plot For Close Price. 
plot(close.price.ts, 
     xlab = "Time", ylab = "Price, $",
     ylim = c (2200, 3600), main = "S&P 500 Close Price in 2019", 
     xaxt = "n", bty = "l", lwd = 2, col="blue")
axis(1, at = seq(1, 252), labels = format(seq(1, 252)))


## TEST PREDICTABILITY OF S&P500 CLOSE STOCK PRICES.

# Use Arima() function to fit AR(1) model for S&P500 close prices.
# The ARIMA model of order = c(1,0,0) gives an AR(1) model.
close.price.ar1<- Arima(close.price.ts, order = c(1,0,0))
summary(close.price.ar1)


# Create differenced ClosePrice data using (lag-1).
diff.close.price <- diff(close.price.ts, lag = 1)
diff.close.price

close.price.ts_lag1 <- c(0, close.price.ts[1:252])
close.price.ts_lag1
diff.close.price_lag1 <- close.price.ts[2:252] - close.price.ts[1:251]
diff.close.price_lag1

# Use plot() function to create plot For first differencing, lag-1. 
plot(diff.close.price_lag1, 
     xlab = "Time", ylab = "Price, $",
     ylim = c (-100, 100), main = "First Differencing of Close Price in 2019", 
     xaxt = "n", bty = "l", lty = 5, lwd = 2, col="orange")
axis(1, at = seq(1, 252), labels = format(seq(1, 252)))

# Develop data frame with close price, close price lag-1, and first
# differencing lag-1.
diff.df <- data.frame(close.price.ts, c("", round(close.price.ts[1:251],2)), 
                      c("", round(diff.close.price_lag1,2)))

names(diff.df) <- c("ClosePrice", "ClosePrice Lag-1", 
                     "First Difference")
diff.df 

# Use Acf() function to identify autocorrealtion for differenced
# ClosePrices and plot autocorrelation for different lags 
# (up to maximum of 12).
Acf(diff.close.price, lag.max = 12, 
    main = "Autocorrelation for S&P500 Differenced Close Prices")


## TEST PREDICTABILITY OF AMTRAK RIDERSHIP.

# Use ts() function to create time series set for Amtrak ridership.
ridership.ts <- ts(Amtrak.data$Ridership, 
                   start = c(1991, 1), end = c(2004, 3), freq = 12)
ridership.ts

# Use Arima() function to fit AR(1) model for Amtrak Ridership.
# The ARIMA model of order = c(1,0,0) gives an AR(1) model.
ridership.ar1<- Arima(ridership.ts, order = c(1,0,0))
summary(ridership.ar1)


# Create differenced Amtrak Ridership data using (lag-1).
diff.ridership.ts <- diff(ridership.ts, lag = 1)
diff.ridership.ts

# Use Acf() function to identify autocorrealtion for differenced 
# Amtrak Ridership, and plot autocorrelation for different lags 
# (up to maximum of 12).
Acf(diff.ridership.ts, lag.max = 12, 
    main = "Autocorrelation for Differenced Amtrak Ridership Data")

