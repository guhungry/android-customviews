# Android Custom Views
[![Build Status](https://travis-ci.org/guhungry/android-customviews.svg?branch=master)](https://travis-ci.org/guhungry/android-customviews)
[![codecov](https://codecov.io/gh/guhungry/android-customviews/branch/master/graph/badge.svg)](https://codecov.io/gh/guhungry/android-customviews)

Android library with Custom Views and Helpers to make my life simpler written in kotlin

## Getting started
### Install dependencies

Add **Android Custom Views** into project as a Gradle compile dependency:
```
implementation 'com.guhungry.android:custom-views:0.0.13'
```
or to remove transitive dependencies use:
```
implementation('com.guhungry.android:custom-views:0.0.13') {
    exclude group: 'com.android.support'
    exclude group: 'org.jetbrains.kotlin'
}
```
[ ![Download](https://api.bintray.com/packages/guhungry1/maven/com.guhungry.android%3Acustom-views/images/download.svg) ](https://bintray.com/guhungry1/maven/com.guhungry.android%3Acustom-views/_latestVersion)

## Components

### Recycler View
#### Grid Spacing Item Decorator
<img src="https://github.com/guhungry/android-customviews/raw/master/documentation/images/recyclerview/GridSpacingItemDecorator.gif" width="300" title="Grid Spacing Item Decorator">

#### Linear Spacing Item Decorator
<img src="https://github.com/guhungry/android-customviews/raw/master/documentation/images/recyclerview/HorizontalLinearSpacingItemDecorator.gif" width="300" title="Horizontal Linear Spacing Item Decorator"> <img src="https://github.com/guhungry/android-customviews/raw/master/documentation/images/recyclerview/VerticalLinearSpacingItemDecorator.gif" width="300" title="Vertical Linear Spacing Item Decorator">

### Listener (Interface)
#### On Load More Listener (for Base Load More Adapter)
#### On Item Click Listener
#### On Page Change Listener (for RecyclerView.addOnPageChangeListener())

### Recycler Adapter
#### Base Load More Adapter (Abstract Class)
<img src="https://github.com/guhungry/android-customviews/raw/master/documentation/images/recyclerview/BaseLoadMoreAdapter.gif" width="300" title="Base Load More Adapter">

#### Base Sectioned Data Adapter (Abstract Class)
<img src="https://github.com/guhungry/android-customviews/raw/master/documentation/images/recyclerview/BaseSectionedDataAdapter.png" width="300" title="Base Sectioned Data Adapter">

### View Holder
#### Bindable View Holder (Abstract Class)

### Extensions
#### RecyclerView.addOnPageChangeListener()
<img src="https://github.com/guhungry/android-customviews/raw/master/documentation/images/recyclerview/PageChangeRecyclerView.gif" width="300" title="RecyclerView.addOnPageChangeListener()">
