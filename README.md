uom-systems
===========

Units of Measurement Systems

[![Circle CI](https://circleci.com/gh/unitsofmeasurement/uom-systems.svg?style=svg)](https://circleci.com/gh/unitsofmeasurement/uom-systems) 
[![Stack Overflow](http://img.shields.io/badge/stack%20overflow-unit%20systems-4183C4.svg)](http://stackoverflow.com/search?q=unit+systems)
[![License](http://img.shields.io/badge/license-BSD3-blue.svg)](http://opensource.org/licenses/BSD-3-Clause)

The project currently contains the following modules:

- [Reusable Quantities](quantity)
- [Common Unit Systems](common)
- [Unified Code for Units of Measure](ucum-java8) on **Java SE 8**

Modular Unit Systems
-------------------------------------
Unit systems are defined in a modular nature. On top of [API](../../../unit-api) and implementations (e.g. [RI](../../../unit-ri)) each application may use one or more unit systems.
![Dependencies](/src/site/resources/images/dependencies.png)

### Environment
Depending on the environment (Embedded, Desktop/Server or a particular Java version) implementations use a different Maven artifactId. Unless explicitly highlighted, all other systems run on every supported environment using the [Reference Implementation](../../../unit-ri))

[![Stories in Ready](https://badge.waffle.io/unitsofmeasurement/uom-systems.png?label=ready&title=Ready)](https://waffle.io/unitsofmeasurement/uom-systems)
[![Stories in Progress](https://badge.waffle.io/unitsofmeasurement/uom-systems.png?label=in%20progress&title=In Progress)](https://waffle.io/unitsofmeasurement/uom-systems)

[![Throughput Graph](https://graphs.waffle.io/unitsofmeasurement/uom-systems/throughput.svg)](https://waffle.io/unitsofmeasurement/uom-systems/metrics)
