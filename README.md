# Nexus Rundeck plugin

<https://github.com/rundeck/nexus-rundeck-plugin>

This is a Nexus plugin that provides an [Option provider](http://rundeck.org/docs/manual/jobs.html#option-model-provider) for [Rundeck](http://rundeck.org).

## Usage

The plugin provides the following new HTTP resources :

- `http://NEXUS_HOST/service/local/rundeck/options/version` : return a json array with the version of the matching artifacts.
  Parameters (all optional) :
  - `r` : repository ID to search in (null for searching in all indexed repositories)
  - `g` : groupId of the artifacts to match
  - `a` : artifactId of the artifacts to match
  - `p` : packaging of the artifacts to match ('jar', 'war', etc)
  - `c` : classifier of the artifacts to match ('sources', 'javadoc', etc)
  - `l` : limit - max number of results to return
  - `includeLatest` : if "true", will include the special "LATEST" version before all versions
  - `includeRelease` : if "true", will include the special "RELEASE" version before all versions
- `http://NEXUS_HOST/service/local/rundeck/options/artifactId` : return a json array with the artifactId of the matching artifacts.
  Parameters (all optional) :
  - `r` : repository ID to search in (null for searching in all indexed repositories)
  - `g` : groupId of the artifacts to match
  - `v` : version of the artifacts to match
  - `p` : packaging of the artifacts to match ('jar', 'war', etc)
  - `c` : classifier of the artifacts to match ('sources', 'javadoc', etc)

Note that if you want to retrieve the artifact from your Rundeck script, you should use [Nexus REST API](https://docs.sonatype.com/display/SPRTNXOSS/Nexus+FAQ#NexusFAQ-Q.HowcanIretrieveasnapshotifIdon%27tknowtheexactfilename%3F).  A simple example is:

    wget "http://NEXUS_HOST/service/local/artifact/maven/content?r=reponame&g=${option.groupId}&a=${option.artifactId}&v=${option.version}" --content-disposition

## Change Log

- Version 1.4
  - Release with support for Nexus 2.8
  - Migrated to JSR-330 annotations
- Version 1.2.2.2 : re-release 1.2, but with a dependence on Nexus 1.9.2.2 (instead of Nexus 1.9)
- Version 1.2 : the option provider for version now includes the date of the version (release)
- Version 1.1 : add option provider for artifactId
- Version 1.0 : option provider for version. compatible with Nexus 1.9 and Rundeck 1.1

## How to install

- Download the latest from <https://github.com/rundeck/nexus-rundeck-plugin/releases>
- Unzip the "nexus-rundeck-plugin-VERSION-bundle.zip" file into the "plugin-repository" directory (located in "$NEXUS_HOME/sonatype-work/nexus/plugin-repository")
  You should have a "nexus-rundeck-plugin-VERSION" directory with a "nexus-rundeck-plugin-VERSION.jar" file inside it
- Then, you just need to restart Nexus.

## How to build

- prerequisites : Apache Maven 3.0.4 - 3.0.5 http://maven.apache.org/
- Java 1.7
- run "mvn package"
- use the "bundle" file in target/nexus-rundeck-plugin-VERSION-bundle.zip

## FAQ

- <https://github.com/rundeck/nexus-rundeck-plugin/wiki/FAQ>

## Resources

- [Nexus](http://nexus.sonatype.org)
- [Rundeck](http://www.rundeck.org)
- [Rundeck "Option Model Provider"](http://rundeck.org/docs/manual/jobs.html#option-model-provider)
- [Rundeck mailing-list (for questions/feedback/etc)](http://groups.google.com/group/rundeck-discuss)

## License

LICENSE : The Apache Software License, Version 2.0
See the LICENSE file, or http://www.apache.org/licenses/LICENSE-2.0

## Credit

Original project by [Vincent Behar](http://github.com/vbehar)