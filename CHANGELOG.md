# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## [V1.0]

## 2024.12.07

### Added

**New Kernel Methods**:
- Added additional `addExchangeRate` kernel method with an `ExchangeRate` argument
- Added `exchangeRateSet` kernel method
- Added `rates` kernel method
- Added `nameSet` kernel method

**New Secondary Method**:
- Added `addAllExchangeRates` secondary method

### Updated

- Changed design to be built on the map data strucure from the Java Collections Framework
- Changed the name of `populateRatesFromAPI` to `getCurrentExchangeRates`


## [Unreleased]

## 2024.12.04

### Added

- Designed test suite for the `RateTable` component
- Designed two different use cases for `RateTable` component

### Updated

- Changed design to include a `populateRatesFromAPI` secondary method, which pulls current exchange rates from an API.


## [Unreleased]

## 2024.11.14

### Added

- Designed kernel implementation for `RateTable` component

### Removed

- Removed `ExchangeRateKernel` and `ExchangeRateSecondary`

### Updated

- Changed design to a `RateTable`, which is a table of `ExchangeRates`. Implemented `ExchangeRate` record


## [Unreleased]

## 2024.11.11

### Added

- Designed abstract class for `ExchangeRate` component

### Removed

- Removed `applyDiscount` method, as it was essentially the same as `multiplyRate`

### Updated

- Changed design to include `isWithinRange` and `isStable` as secondary methods, and added `getRateName`, `getRateValue`, and `setRateMap` to kernel methods


## [Unreleased]

## 2024.10.17

### Added

- Designed kernel and enhanced interfaces for thw `ExchangeRate` component

### Updated

- Changed design to include `isWithinRange` and `isStable` kernel methods, as well as `convertAmount` and `applyDiscount` secondary methods. Removed `setRate` kernel method and `compareValue` secondary method


## [Unreleased]

## 2024.10.03

### Added

- Designed a proof of concept for `ExchangeRate` component

### Updated

- Changed design to exclude the `getRates` kernel method, and added a secondary method: `compareValue`, and changed component brainstorming document to reflect these changes


## [Unreleased]

## 2024.09.16

### Added

- Designed an `ExchangeRate` component
- Designed a `WorkoutProgram` component
- Designed a `Playlist` component
