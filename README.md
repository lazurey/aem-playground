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

**Warning:** `npm install` is skipped in the maven build to reduce the build time. If you want to reuse the code here, please add this step and find a way to optimize the time it needs. And it needs to run `npm install` under `design/src/main/react-app/` for the 1st time. 

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

### React component on AEM

* [x] Server side render React component
* [x] Client side render React component

#### Server side render React component

A typical server side render React component could be like this:

```
- Component
  - .content.xml
  - dialog.xml
  - Component.jsp
  - template.jsx
```

`.content.xml` and `dialog.xml` are the same as normal components.
`Component.jsp` will be a shell template and only used to call the component call to render.
`template.jsx`: your React component will stay here.

To enable the server side render, it will need an abstract component class to call the Nashorn ScirptEngine in Java and all AEM components need to extend this class and change the data fetch method. Refer to the [ReactComponent.java](https://github.com/lazurey/aem-playground/blob/master/samples/src/main/java/com/lazurey/www/samples/foundation/ReactComponent.java), I haven't worked out how to support JSX at the moment.

Although server render is feasible, I didn't see there's much benefit we could get from this approaching.

#### Client side render React component
The idea is to find a way to do client-render for React components. 

I postulate that the start point is:

- React project (e.g. Style guid) is maintained separately by another team.
- AEM project will try to use the existing React project and do the integration work.

Based on this, I used [React-MDL](https://tleunen.github.io/react-mdl/) as Style guide and wrote a [JavaScript helper](https://github.com/lazurey/aem-playground/blob/master/design/src/main/resources/jcr_root/etc/designs/react-comp/clientlibs/script/react-render-tool.js) function to render React component based on the components on the page.

The component template will be a placeholder like this:

```html
<div class="component-placeholder"
      data-comp-name="Button"
      data-uri="<%= currentNode.getPath() %>.json"></div>
```

If you want to customize the json, then you could write something in the server side to shape the data into something that the React component needs.

A few facts about this approach:

- It's client-side rendered
- It will have limited editting functionalities and will need a refresh unless paying more effort to fix this.
- It should be only used for non-cachable components and better be less than 10% of a page.

#### Opinion

This could be a reasonable way to use React on AEM because Style guide will always be a separate team and it could be already done before AEM set up. And we always need to use styleguide to implement something.

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
