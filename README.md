# AEM Playground

Trying different possibilities on AEM and looking for better approaches to integrate AEM with no matter modern front-end frameworks or to implement AEM with engineering practises.

## Prerequisite

- Java 1.8
- Maven 3.2.5
- AEM 6.2

**Warning:** The project has been *ONLY* tested on Mac OSX.

## Build

Make sure your AEM instance started.

`mvn clean install -P autoInstall`

**Note:** If you are not using default port or username/password, please change the configuration in `pom.xml` accordingly.

## Inside this project

* [x] AngularJS SPA on AEM
* [x] ReactJS SPA on AEM
* [ ] Client render React component on AEM

### AngularJS SPA on AEM

The spike followed [Adobe's guild](https://helpx.adobe.com/experience-manager/using/AngularJS.html) of how to integrate AngularJS with AEM.

The main idea of the guild is to set up a page only for Angular Application, it's *NOT* about how to use Angular in components.
Basically, the page imports an angular.js and your application JavaScript, and that's all.

### ReactJS SPA on AEM
There are a few options to integrate React on AEM and I'm doing spikes for 2 of them.

#### Approach

Build an independent React App outside of AEM structure, and using AEM template to link js files to the page.
The template is located at `content/src/main/content/jcr_root/apps/aem-playground/facebook/page-react/` and please refer to `customfooterlibs.html` for the code.

React app is located at `design/src/main/react-app/` and via `webpack` build and copy the `bundle.js` to AEM design folder (`design/src/main/resources/jcr_root/etc/designs/react-libs/clientlibs/script/`).

By using Maven `exec-maven-plugin`, it will execute `npm run build` in the Maven build process.

**Note:** Please note that this React App is *NOT* using any AEM API or any data from AEM.

**Warning:** `npm install` is skipped in the maven build to reduce the build time. If you want to reuse the code here, please add this step and find a way to optimize the time it needs.

#### Opinion
It's the easist way to use React on AEM, but it's also a meaningless way.
This is like Sushi, putting a piece of adorable protein (React) on the costly rice (AEM) with *NO attachment* between them.

Still I will recommend this way, because at least, it's harmless to both AEM and React.

#### What's in the React App

* [x] ES6
* [x] Webpack
* [x] Style integration
* [x] Router
* [ ] Redux
* [ ] Mocha

#### Known issues

* Router and URL: the URL can't reflex the actual address.

### Client render React component on AEM

Code to be done.

#### Approach
The idea is to find a way to do client-render for React components. It aims to enable applications to cache a page as much as it could by making the customized content client-side rendering.

A few facts about this idea:

- It's client-side rendered
- It will have limited editting functionalities
- It should be only used for non-cachable components and better be less than 10% of a page.

Technical it will need:

- JavaScript engine in Java

#### Opinion

TBD

### React + AEM Integration Alternative

#### Opinion
I'm not a supporter on deep integrating React with AEM. If you're looking for solutions for this situation, please refer to [@sinnerschrader](https://github.com/sinnerschrader) work on [AEM + React](https://github.com/sinnerschrader/aem-react).

For the following aspects, I think that is a bad idea:

- Huge effort spent on packing AEM API for React components;
- Limited AEM functionalities can be used in this approach;
- React can only be used as **view** templating here, no data stream management;
- React can only be server-rendered;
- It's not AEM nor React, make no sense to me (as a UI dev);
- Learning curve will be even steeper than AEM/React, because developers will need to learn this API and it won't be used in anywhere else.
