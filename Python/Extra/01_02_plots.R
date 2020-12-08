
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
## PLOT TIME SERIES DATA. 

# Function ts() takes three arguments: start, end, and freq.
# With monthly data, frequency (freq) of periods in a season (year) is 12. 
# With quarterly data, frequency in a season (year) is equal to 4.
# Arguments start and end are pairs: (season number, period number).
ridership.ts <- ts(Amtrak.data$Ridership, 
                   start = c(1991, 1), end = c(2004, 3), freq = 12)

## Use plot() to plot time series data  
plot(ridership.ts, 
     xlab = "Time", ylab = "Ridership (in 000s)", 
     ylim = c(1300, 2300), main = "Amtrak Ridership", col = "blue")


## DEVELOP REGRESSION MODELS FOR TIME SERIES DATA.
## PLOT TIMES SERIES DATA WITH REGRESSION TRENDLINES.

# Use tslm() function to create linear trend (rideship.lin) and 
# quadratic trend (rideship.quad) for time series data. 
ridership.lin <- tslm(ridership.ts ~ trend)
ridership.quad <- tslm(ridership.ts ~ trend + I(trend^2))

# Use plot() function to create plot with linear trendline. 
plot(ridership.ts, 
     xlab = "Time", ylab = "Ridership (in 000s)",
     ylim = c (1300, 2300), main = "Amtrak Ridership with Linear Trendline", 
     col="blue")
lines(ridership.lin$fitted, lwd = 2)

# Use plot() function to create plot with quadratic trendline. 
plot(ridership.ts, 
     xlab = "Time", ylab = "Ridership (in 000s)",
     ylim = c (1300, 2300), main = "Amtrak Ridership with Quadratic Trendline", 
     col="blue")
lines(ridership.quad$fitted, lwd = 2)


## ZOOM-IN PLOT OF TIME SERIES DATA.

# Create zoom-in plot for 3 years from 1997 through 1999.
ridership.ts.3yrs <- window(ridership.ts, start = c(1997, 1), end = c(1999, 12))

plot(ridership.ts.3yrs, 
     xlab = "Time", ylab = "Ridership (in 000s)", 
     ylim = c (1300, 2300), main = "Amtrak Ridership for 3 Years", 
     col = "blue")

autoplot(ridership.ts.3yrs, ylab = "Ridership (in 000s)", 
         main = "Amtrak Ridership for 3 Years", col = "blue", lwd = 1)

