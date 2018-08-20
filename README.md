# Android Custom Views
[![Build Status](https://travis-ci.org/guhungry/android-customviews.svg?branch=master)](https://travis-ci.org/guhungry/android-customviews)
[![codecov](https://codecov.io/gh/guhungry/android-customviews/branch/master/graph/badge.svg)](https://codecov.io/gh/guhungry/android-customviews)

Android library with Custom Views and Helpers to make my life simpler written in kotlin

## Getting started
### Install dependencies

Add **Android Custom Views** into project as a Gradle compile dependency: 
```
implementation 'com.guhungry.views:views:0.0.4'
```
or to remove transitive dependencies use:
```
implementation('com.guhungry.views:views:0.0.4') {
    exclude group: 'com.android.support'
    exclude group: 'org.jetbrains.kotlin'
}
```
[ ![Download](https://api.bintray.com/packages/guhungry1/maven/com.guhungry.views%3Aviews/images/download.svg) ](https://bintray.com/guhungry1/maven/com.guhungry.views%3Aviews/_latestVersion)

## Components

### Recycler View
#### Grid Spacing Item Decorator
<img src="https://github.com/guhungry/android-customviews/raw/master/documentation/images/recyclerview/GridSpacingItemDecorator.gif" width="300" title="Grid Spacing Item Decorator">

#### Linear Spacing Item Decorator
<img src="https://github.com/guhungry/android-customviews/raw/master/documentation/images/recyclerview/HorizontalLinearSpacingItemDecorator.gif" width="300" title="Horizontal Linear Spacing Item Decorator"> <img src="https://github.com/guhungry/android-customviews/raw/master/documentation/images/recyclerview/VerticalLinearSpacingItemDecorator.gif" width="300" title="Vertical Linear Spacing Item Decorator">

### Listener (Interface)
#### On Load More Listener
#### On Item Click Listener

### Recycler Adapter
#### Base Load More Adapter (Abstract Class)
<img src="https://github.com/guhungry/android-customviews/raw/master/documentation/images/recyclerview/BaseLoadMoreAdapter.gif" width="300" title="Base Load More Adapter">

### View Holder
#### Bindable View Holder
