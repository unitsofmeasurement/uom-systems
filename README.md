uom-systems
===========

Units of Measurement Systems

[![Maven Central](https://img.shields.io/maven-central/v/systems.uom/systems-parent)](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22systems.uom)
[![Circle CI](https://circleci.com/gh/unitsofmeasurement/uom-systems.svg?style=svg)](https://circleci.com/gh/unitsofmeasurement/uom-systems)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/73d860c4f9e742eabea067c181b51ed7)](https://www.codacy.com/gh/unitsofmeasurement/uom-systems/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=unitsofmeasurement/uom-systems&amp;utm_campaign=Badge_Grade)
[![Stability: Active](https://masterminds.github.io/stability/active.svg)](https://masterminds.github.io/stability/active.html)
[![License](http://img.shields.io/badge/license-BSD3-blue.svg)](http://opensource.org/licenses/BSD-3-Clause)
[![Stack Overflow](http://img.shields.io/badge/stack%20overflow-unit%20systems-4183C4.svg)](http://stackoverflow.com/search?q="unit+systems")

The project contains the following modules:

- [Reusable Quantities](quantity)
- [Common Unit Systems](common) like [US customary units](https://en.wikipedia.org/wiki/United_States_customary_units) or [Imperial units](https://en.wikipedia.org/wiki/Imperial_units)
- [Unicode CLDR Unit System](unicode)
- [Unified Code for Units of Measure](ucum)

Modular Unit Systems
-------------------------------------
Unit systems are defined in a modular nature. On top of [API](../../../unit-api) and implementations (e.g. [RI](../../../indriya)) each application may use one or more unit systems.
![Dependencies](/src/site/resources/images/dependencies.png)

Also see [The history of unit systems](https://en.wikipedia.org/wiki/International_System_of_Units#History)

### Environment
Depending on the environment (Embedded, Desktop/Server or a particular Java version) implementations use a different Maven artifactId. Unless explicitly highlighted, all other systems run on every supported environment using the [Reference Implementation](../../../indriya).

Planning
------------
[![Average time to resolve an issue](http://isitmaintained.com/badge/resolution/unitsofmeasurement/uom-systems.svg)](http://isitmaintained.com/project/unitsofmeasurement/uom-systems "Average time to resolve an issue")
[![Percentage of issues still open](http://isitmaintained.com/badge/open/unitsofmeasurement/uom-systems.svg)](http://isitmaintained.com/project/unitsofmeasurement/uom-systems "Percentage of issues still open")
