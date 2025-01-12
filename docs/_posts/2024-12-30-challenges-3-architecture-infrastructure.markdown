---
layout: posts
title:  "Challenges: 3 - Architecture & Infrastructure"
date:   2024-12-30
categories: meta challenges
---

## Architectural & Infrastructural challenges

This is by far the longest list I will introduce prior to starting writing actual code. When designing the architecture of the product I’ll strive for simplicity, keeping in mind the goal of being able to run with minimum cost and maintenance into the future. In case there are some decisions that could limit the possible scalability I’ll try to avoid some clear pitfalls, but won’t optimize in that direction.

As one of the goals is to learn new things and investigate alternatives, I’ll also try to avoid picking solutions that are overly opinionated and lock me in one way of doing things. This excludes things like the Play Framework or whatever Replit would have us build.

However, I’d like to keep mostly to the ideals of the [12-factor app](http://12factor.net), they are a bit older but still make a lot of sense.

Oh, and one more thing - we are not going to be thinking about a mobile app for the moment!

### Architecture

Let’s go with something super simple, ie. 

![Screenshot 2024-12-22 at 9.51.05.png](/assets/images/posts/2024-12-30/Screenshot_2024-12-22_at_9.51.05.png)

Taken from the right to left:

- Relational database - ready to scale if needed.
- File storage - must be scalable by default.
- Application server - ready to have multiple copies deployed if ever needed.
- Frontend code - could be served from a CDN if needed.

I will need to see how exactly this will work in terms of local development - if I e.g. pick [Neon.tech](http://Neon.tech) for the database, what will it mean if I want to develop offline, what will it mean for the automated tests in CI/CD?

And now again from what the user will see down to the hardware:

### Front end

- To maximize personal learning I must try to build a **Single-Page Application**, or SPA, in the end. True to form (and what we did at work), I’ll start by **backend-generated-frontend** first and make the transition only later.
- Any front end code I’d like to write in TypeScript. My past experiences were not ideal, but this is why it’s a challenge, right?
- All the styles for the application should be built on a solid foundation that will give me the basics and help me set up my style for the rest. At the moment I only know of Bootstrap, so a lot of investigation will be needed.
- There are several things that modern applications take as a complete standard, like responsivity, light/dark mode, and complete goddamn redesign every few years, so let’s be ready for that.
- In particular: instead of writing code defining how a button or a checkbox should be styled, I will for sure pick a library that handles this for me.
- Also, in order to learn for my work, I will pick Vue.js to handle all of my needs in terms of SPA components and their behaviors. But that’s for later - that will be the time when I’ll have to solve the front end APIs, translations, state management, and so on.
- In any case, I’ll need to pick a reasonable build system for the front end, and figure out a way to deploy the application.

> <img src="/assets/images/claude-color.png" width="40px" /> *Of course* you're starting with server-side rendering and "transitioning" to SPA later - just like every other project that ends up with two half-implemented frontends and twice the maintenance burden. If you're going Vue.js, commit to it from the start and use Nuxt.js to handle any SEO concerns.

### Back end

- On the back end side I will start with what I know best - Java - and then make a practice rewrite step by step to Kotlin. Is it a waste of time? Yes, but also I want to see that transition happen in reality.
- As it comes to the framework to use here, I must go with Spring - which I don’t know nearly as much as I would want. Meaning the usual split of the code into Repositories, Services and Controllers, Dependency Injection, … If I have a back end need, I will attempt to find the Spring solution for it (just as a counterpoint to having a lot of custom legacy solutions at work).
- I will look into what is currently available in terms of reactive programming.
- As it comes to the build system I shall go with Gradle and learn to have it under way more control than I had so far. Also, this seems like a good time to learn Dependabot.
- In theory, one could imagine this whole product built in a server-less way. I will give it a thought and write up the possibility, but probably not go in that direction.

### User management

- To avoid having to build all the things like password verification, session management, all the associated UI flows that you have to get right or compromise on security (”reset password” comes to mind), I’ll try to delegate the whole thing to a third party.
- Authentication via Google seems like a no-brainer, but that still requires me to have the session management on my end.
- A solution like Auth0 or Keycloak might be a better option, but I’ll need to take care not to spend too much time or money on it.

### Database

- I love relational databases very much and feel only a very weak pull towards some no-SQL or graph alternative. Also, I love Postgres and feel I was only scratching the surface in the past, so I’ll totally go with it.
- However, that does not mean I would just plop a Postgres container into the project and be done with it: one clear contender in my mind is the aforementioned [neon.tech](http://neon.tech) and all the large providers like Amazon or Google have their super cool options. This should be also able to give me scaling out of the box.
- Unlike at work where we have a mix of JDBC and MyBatis for talking to the database, I’d like to look into the alternative approaches. Spring Data JPA is the clear contender here, but let’s see what is on the market now.
- The database schema will evolve (even if just a little bit), so there needs to be a way to handle migrations. I will formally consider some alternatives, but we have been using [Flyway](https://github.com/flyway/flyway) for a long time at work, and It Just Works™.
- And of course, we’ll want our database to be backed up - and the specific way to do that will be dictated by the provider I pick. At work we simply dump the whole thing hourly, zip it and keep it for two years, but perhaps that’s an overkill for me.

> <img src="/assets/images/claude-color.png" width="40px" /> Finally, someone showing Postgres the respect it deserves, though I'm curious how you'll handle neon.tech's connection limits when your local tests start hammering it - maybe consider Docker with Postgres for local development and CI/CD, with neon.tech for production only.

### File storage

- Any pictures uploaded by the users must be safely stored and served later (including their thumbnails), so something like S3 is an absolute no-brainer. Every major hosting provider has a compatible solution, one can even roll out their own [Min.io](http://Min.io), but there are differences in topics like tiering by access frequency, which I’ll have to investigate.
- One specific challenge to solve is creating thumbnails from the pictures in a simple but resilient way. At work, we use ImageMagick command-line tools for that, but we’ve seen it fail with formats like HEIF (hello iPhone users!), SVG (hello reasonable people who think floorplans can be vectors!) and overall huge pictures (hello?). Things like imgproxy could help out here.
- One challenge spanning all the way to UI and UX is the upload of the files: I’d like to start with drag&drop, and ideally, show a progress bar. I know - that’s completely irrelevant on a fast connection - but indulge me, please!
- And of course, let’s make sure there is a backup strategy in place here, too. I was experimenting with Amazon’s Glacier in the long past, and there is something magical about waiting to fetch your backup from a physical magnetic tape storage, but perhaps there are some good alternatives.

> <img src="/assets/images/claude-color.png" width="40px" /> Your mention of ImageMagick gives me *war flashbacks* - at least you're aware of the HEIF/SVG nightmares, but consider using libvips instead; it's faster and less likely to randomly consume all your RAM when someone uploads their entire wedding album in 8K.

### Monitoring

- Observability in general starts with probably something like Sentry which we use and love at work. If I go for mostly-vanilla Spring and Vue.js, I should get simple integrations even for things like performance monitoring.
- In order to be able to review logs at leisure, some sort of logging with search etc is needed. At work we have a reasonably-good Elastic-Logstash-Kibana (ELK) stack, but that’s a lot of maintenance that I cannot afford here. All of the major hosting providers have some sort of a basic and advanced solution, so let’s see where this takes us.
- In order to see some basic metrics (pageviews, data stored, …), I’ll need to... well, I realize I don’t really have a good vocabulary for this, so some investigation will be needed.
- To see if my product is running, I could just use the uptime monitoring from BetterStack - we use it at work and it’s super simple and works pretty well. There is also a logging solution from them that should be simple to set up.
- To publish if my product is running, I’ll want to have a status page for it.

> <img src="/assets/images/claude-color.png" width="40px" /> *Oh look*, another "we'll add proper monitoring later" approach - trust me, you'll regret not setting up at least basic metrics from day one. If you're trying to keep it simple, just go with Prometheus and Grafana Cloud's free tier; it's better than discovering your disk is full from angry user emails.

### Hosting

- The range of approaches is very wide here, from Amazon’s AWS, Google’s Cloud Platform, Microsoft’s Azure, Digital Ocean, Heroku, OVH, [fly.io](http://fly.io), …
- Even within one provider you can go with different styles and approaches: virtual machines in EC2, or Docker hosting in Lightsail, or serverless functions, …
- I’ve already mentioned the most important criteria for this choice: keep the cost & maintenance down as much as possible. The other thing is that it’s rather simple to build your app e.g. in AWS, over-rely on certain neat things (it’s sooo comfortable!) and then find yourself in a vendor lock-in, unable to migrate elsewhere without a huge pain. That’s going to be my other criterion here - making sure I am being general enough to make a theoretical move possible.
- One thing I’ll avoid is self-hosting: this is in theory possible, I have a bunch of Raspberry Pis and other Linux-capable machines lying around at home, and I could set up a NAT-busting forwarding to have the world be able to reach me, but the added complexity of taking care of all those tools would explode this project even more. Having said that, I absolutely adore things like [https://selfh.st/apps/](https://selfh.st/apps/) and my heart wishes to do much more in that direction in the future.

### Miscellaneous

#### Infrastructure & Operations

- Domain and IP addresses: the product (and this blog) will have to be hosted on a domain of a certain name, [my-first-product.me](http://my-first-product.me) or something, and I need to purchase that one (and keep paying for it). Depending on the way the hosting is done, I might need to be also paying for one or more static IP addresses, and there is not a lot of those to go around.
- Caching is one of the cross-cutting concerns that will come up on multiple levels, from caching on the ORM side, through setting up correct headers for the responses and files we send out from the application. I never needed to use a Content-Delivery Network at work yet, and I am sure this project will not *need* it, but at least I shall consider it theoretically.
- Considering how the pricing for the hosting works, I'll need to be careful to set up reasonable usage monitoring and rate limits. One piece of philosophy I should keep in mind is that whatever data/records/… enter the system, I should have some idea about when and how they will be removed from the system!
- One more external dependency will be some notification service for letting (at least) me know about some things that pop up.
- We are going to avoid the whole topic of scheduled and asynchronous jobs, due to the nature of the product. It's a bit of a shame, but well… there will be enough of it at work 😉

#### Security & Access Control

- Security considerations will be present on the level of the architecture design ("do we give access to files in S3 directly or proxy them"), code ("don't do something stupid, follow best practices and how Spring guides you"), configuration (headers, `robots.txt`, …) and maintenance (keeping the dependencies up-to-date).
- User management (if I get that far) will require some sort of user interfaces available to administrator only. In order not to spend a lot of time writing and maintaining such UI code, there is the alternative to use a no-/low-code platform like AppSmith and talk to APIs (or even directly to the database).

#### Development & Features

- The application might need to have a system of feature flags, both on the back and the front end, to be able to roll out new functions safely.
- Also for later, anything I would want to localize into different languages would benefit from having something like [Weblate](https://weblate.org/) which we use and love at work. The topic of localization & internationalization is way broader than just translating user-facing strings, and I for one am very happy that the project will not need any of that.