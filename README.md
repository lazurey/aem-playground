# AEM Playground

Trying different possibilities on AEM and looking for better approaches to integrate AEM with no matter modern front-end frameworks or to implement AEM with engineering practises.

## Prerequisite

- Java 1.8
- Maven 3.2.5
- AEM 6.2

The project has **only** been tested on Mac OSX.

## Build

**Local Build**  

Make sure you start AEM instance first.

`mvn clean install -P autoInstall`

## Inside this project

* [x] AngularJS SPA on AEM
* [x] ReactJS SPA on AEM
* [] Client render React component on AEM

### AngularJS SPA on AEM
Done

### ReactJS SPA on AEM
### Approach

Build an independent React App outside of AEM structure, and using AEM template to link the js file to the page.
The template is located at `content/src/main/content/jcr_root/apps/aem-playground/facebook/page-react/` and please refer to `customfooterlibs.html` for the code.

React app is located at `design/src/main/react-app/` and via `webpack` build and copy the `bundle.js` to AEM design folder (`design/src/main/resources/jcr_root/etc/designs/react-libs/clientlibs/script/`).

By using Maven `exec-maven-plugin`, it will execute `npm run build` in the Maven build process.

**Note:** Please note that this React App is *NOT* using any AEM API or any data from AEM.

**Warning:** `npm install` is skipped in the maven build to reduce the build time. If you want to reuse the code here, please add this step and find a way to optimize the time it needs.

### What's in the React App

* [x] ES6
* [x] Webpack
* [] Style integration
* [] Router
* [] Redux
* [] Mocha

### Client render React component on AEM
TBD