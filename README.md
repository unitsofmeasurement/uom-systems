uom-systems
===========

Units of Measurement Systems
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/systems.uom/systems-parent/badge.svg)](https://maven-badges.herokuapp.com/maven-central/systems.uom/systems-parent)
[![Circle CI](https://circleci.com/gh/unitsofmeasurement/uom-systems.svg?style=svg)](https://circleci.com/gh/unitsofmeasurement/uom-systems) 
[![Stack Overflow](http://img.shields.io/badge/stack%20overflow-unit%20systems-4183C4.svg)](http://stackoverflow.com/search?q=unit+systems)
[![License](http://img.shields.io/badge/license-BSD3-blue.svg)](http://opensource.org/licenses/BSD-3-Clause)
<!--
[![Issue Stats](http://issuestats.com/github/unitsofmeasurement/uom-systems/badge/pr?style=flat)](http://issuestats.com/github/unitsofmeasurement/uom-systems)
[![Issue Stats](http://issuestats.com/github/unitsofmeasurement/uom-systems/badge/issue?style=flat)](http://issuestats.com/github/unitsofmeasurement/uom-systems)
-->
The project currently contains the following modules:

- [Reusable Quantities](quantity)
- [Common Unit Systems](common) like [US customary units](https://en.wikipedia.org/wiki/United_States_customary_units) or [Imperial units](https://en.wikipedia.org/wiki/Imperial_units)
- [Common Unit Systems](common-java8) on **Java SE 8**
- [Unicode CLDR Unit System](unicode)
- [Unicode CLDR Unit System](unicode-java8) on **Java SE 8**

Modular Unit Systems
-------------------------------------
Unit systems are defined in a modular nature. On top of [API](../../../unit-api) and implementations (e.g. [RI](../../../unit-ri)) each application may use one or more unit systems.
![Dependencies](/src/site/resources/images/dependencies.png)

Also see [The evolution of unit systems](http://info.ee.surrey.ac.uk/Workshop/advice/coils/unit_systems/#sug)

### Environment
Depending on the environment (Embedded, Desktop/Server or a particular Java version) implementations use a different Maven artifactId. Unless explicitly highlighted, all other systems run on every supported environment using the [Reference Implementation](../../../unit-ri).

[![Stories in Ready](https://badge.waffle.io/unitsofmeasurement/uom-systems.png?label=ready&title=Ready)](https://waffle.io/unitsofmeasurement/uom-systems)
[![Stories in Progress](https://badge.waffle.io/unitsofmeasurement/uom-systems.png?label=in%20progress&title=In Progress)](https://waffle.io/unitsofmeasurement/uom-systems)

[![Throughput Graph](https://graphs.waffle.io/unitsofmeasurement/uom-systems/throughput.svg)](https://waffle.io/unitsofmeasurement/uom-systems/metrics)