# Building AnATLyzer with Maven

This project uses Maven with Tycho to build Eclipse plugins as Maven artifacts.

## Prerequisites

- Java 11 or higher
- Apache Maven 3.6+

## Building the Project

To build all plugins and features:

```bash
mvn clean install
```

To skip tests during the build:

```bash
mvn clean install -DskipTests
```

## Maven Artifacts

The build produces the following libraries that can be deployed to Maven repositories:

- **anatlyzer.atl.typing** - Core typing analysis for ATL transformations
- **anatlyzer.use.witness** - USE witness generation
- **anatlyzer.atl.standalone.api** - Standalone API for programmatic usage

All artifacts are built with:
- Group ID: `io.github.anatlyzer`
- Version: `0.8.0`

## Build Configuration

- **Tycho version**: 4.0.4
- **Target Eclipse version**: 2023-03
- **Java version**: 11

## Excluded Components

The following plugins are excluded from the build due to missing external dependencies:
- `anatlyzer.efinder.witness` (requires efinder.core)
- `anatlyzer.ide` (requires org.eclipse.nebula.widgets.treemapper)
- `anatlyzer.visualizer`
- `esolver.aql` (requires Sirius)

The following features are excluded:
- `anatlyzer.feature.experiments` (depends on experiments folder)
- `anatlyzer.feature.visualization` (depends on excluded plugins)

## Build Structure

```
anatlyzer/
├── pom.xml                    # Parent POM
├── plugins/
│   ├── pom.xml               # Plugins parent POM
│   ├── anatlyzer.atl.typing/
│   ├── anatlyzer.use.witness/
│   └── anatlyzer.atl.standalone.api/
└── releng/
    ├── pom.xml               # Features parent POM
    └── anatlyzer.feature/
```

## Troubleshooting

If you encounter build errors:

1. Ensure you're using Java 11 or higher: `java -version`
2. Check Maven version: `mvn -version`
3. Clean the build: `mvn clean`
4. Update dependencies: `mvn dependency:resolve`

For more information, see the [wiki](https://github.com/jesusc/anatlyzer/wiki/Building-from-sources).
