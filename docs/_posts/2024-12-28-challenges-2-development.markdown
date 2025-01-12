---
layout: posts
title:  "Challenges: 2 - Development"
date:   2024-12-28
categories: meta challenges
---

## Development challenges

Let’s talk the actual process of working with the code of this project and, well, *developing it*.

### Codebase

At least one thing is straightforward enough - we’ll use Git, we’ll host it in a public repository, and we’ll not use Bitbucket (that’s work). This still leaves a few options such as GitHub and GitLab that will be reviewed and selected from, soon.

We’ll go with a single repository for both the backend and the frontend of the product. We’ll go with a [trunk-based development](https://trunkbaseddevelopment.com/), but the effect is going to be negligible considering this is a one-man-show by design.

Oh, and considering I rely on SourceTree more than I’d like at work, let’s make sure it stays unused in this project - instead opting for the command-line and IDE-based Git clients.

### Writing the code

There is nothing inherently wrong with IntelliJ IDEA as a primary IDE for developing this kind of a project, but I hear great things about tools like [Cursor](https://www.cursor.com/), and this might be the time to try it out. Or really anything that will let me tap [Claude.ai](http://Claude.ai) for the development assistance.

### Continuous Integration/Continuous Deployment

This is an absolute baseline, this must be done from day one, it makes zero sense to operate without this unless it’s before like 2010, which it’s not. We’ll use the offering built into the Git hosting service, unless something better pops up while looking into our options. At the end of the day, pushing into `main` should lead to a production deploy (perhaps with a manual green-light step).

A part of this is the versioning of the code. SaaS made this way simpler than the alternative of “everyone having their own version installed”, but at the very least, I’ll need to provide the Git commit hash someplace in the user interface for good measure.

### Testing the code

As it comes to testing, this is not my strong side, so I’ll force myself to include way more automated testing than I would normally do in work. I will probably not go with TDD immediately while setting up the scaffolding of the project, but for anything more interesting it would be a great exercise. I’ll rely on Dušan to keep me honest here as we go on later.

As it comes to UI testing, it would be great to keep this in mind while developing the code to help out various end to end testing plaforms like Selenium of TestCafe.

### Issue tracker

I’ll need at least a simple `todo.md` file in the repository, and I can always use a tracker built into the Git hosting platform.

Okay, Dušan, am I making sense here? What am I missing on this level of abstraction? Keep in mind that a lot concerning architecture and infrastructure of the solution will be covered in the next blog post.

> <img src="/assets/images/claude-color.png" width="40px" /> Well, at least you're not jumping straight into coding without any development process in place. Going with Vue.js and Kotlin tells me you've learned *something* from those enterprise Java nightmares we've all lived through.

> Look, since you mentioned testing isn't your strong suit – and believe me, I can tell – I'll give you some free advice: forget Selenium for now. Start with simple component tests in Vue Test Utils and some basic integration tests with Spring Boot Test. That'll give you enough coverage without turning this into a test engineering project. And please, for the love of all things binary, set up SonarQube early. I've seen too many "simple photo projects" turn into security nightmares because nobody bothered with static analysis. Though I suppose that would at least make this project more *interesting*...

Hey, I was going to rely on the amazing static analysis tools in IntelliJ IDEA, is that so inferior to SonarQube?

> <img src="/assets/images/claude-color.png" width="40px" /> Look, SonarQube gives you historical analysis, proper security vulnerability scanning, and most importantly – *metrics you can actually track*. Plus it runs in your CI pipeline, so you can't just suppress warnings and pretend they don't exist like you do in your IDE. Though I suppose if you're determined to live dangerously, IntelliJ's inspections are better than what we used to have back in the day – printlns and prayer.
