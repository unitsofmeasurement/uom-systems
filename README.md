uom-systems
===========

Units of Measurement Systems

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/systems.uom/systems-parent/badge.svg)](https://maven-badges.herokuapp.com/maven-central/systems.uom/systems-parent)
[![Circle CI](https://circleci.com/gh/unitsofmeasurement/uom-systems.svg?style=svg)](https://circleci.com/gh/unitsofmeasurement/uom-systems)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f1ce1fbf3ff24b67a48592c27869d023)](https://www.codacy.com/app/unitsofmeasurement/uom-systems?utm_source=github.com&utm_medium=referral&utm_content=unitsofmeasurement/uom-systems&utm_campaign=badger)
[![Stability: Active](https://masterminds.github.io/stability/active.svg)](https://masterminds.github.io/stability/active.html)
[![License](http://img.shields.io/badge/license-BSD3-blue.svg)](http://opensource.org/licenses/BSD-3-Clause)
[![Stack Overflow](http://img.shields.io/badge/stack%20overflow-unit%20systems-4183C4.svg)](http://stackoverflow.com/search?q="unit+systems")

The project currently contains the following modules:

- [Reusable Quantities](quantity)
- [Common Unit Systems](common) like [US customary units](https://en.wikipedia.org/wiki/United_States_customary_units) or [Imperial units](https://en.wikipedia.org/wiki/Imperial_units)
- [Unicode CLDR Unit System](unicode)
- [Unified Code for Units of Measure](ucum)

Modular Unit Systems
-------------------------------------
Unit systems are defined in a modular nature. On top of [API](../../../unit-api) and implementations (e.g. [RI](../../../indriya)) each application may use one or more unit systems.
![Dependencies](/src/site/resources/images/dependencies.png)

Also see [The evolution of unit systems](http://info.ee.surrey.ac.uk/Workshop/advice/coils/unit_systems/#sug)

### Environment
Depending on the environment (Embedded, Desktop/Server or a particular Java version) implementations use a different Maven artifactId. Unless explicitly highlighted, all other systems run on every supported environment using the [Reference Implementation](../../../indriya).

Planning
------------
[![Average time to resolve an issue](http://isitmaintained.com/badge/resolution/unitsofmeasurement/uom-systems.svg)](http://isitmaintained.com/project/unitsofmeasurement/uom-systems "Average time to resolve an issue")
[![Percentage of issues still open](http://isitmaintained.com/badge/open/unitsofmeasurement/uom-systems.svg)](http://isitmaintained.com/project/unitsofmeasurement/uom-systems "Percentage of issues still open")
