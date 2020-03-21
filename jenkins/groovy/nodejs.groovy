#!/usr/bin/env groovy
import hudson.tools.InstallSourceProperty
import jenkins.model.Jenkins
import jenkins.plugins.nodejs.tools.NodeJSInstallation
import jenkins.plugins.nodejs.tools.NodeJSInstaller
import static jenkins.plugins.nodejs.tools.NodeJSInstaller.DEFAULT_NPM_PACKAGES_REFRESH_HOURS

final versions = [
        'NodeJS 13.x': '13.10.1',
        'NodeJS 12.x': '12.16.1'
]

final globalPackages = "eslint@~6.8.0 truffle@~5.1.18"

Jenkins.instance.getDescriptor(NodeJSInstallation).with {
    installations = versions.collect {
        new NodeJSInstallation(it.key, null, [
                new InstallSourceProperty([
                        new NodeJSInstaller(
                            it.value,
                            globalPackages,
                            DEFAULT_NPM_PACKAGES_REFRESH_HOURS)
                ])
        ])
    }  as NodeJSInstallation[]
}