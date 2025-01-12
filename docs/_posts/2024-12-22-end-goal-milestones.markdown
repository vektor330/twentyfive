---
layout: posts
title:  "The End Goal and Milestones"
date:   2024-12-22
categories: meta
---

## The End Goal

Let’s quickly describe the intended end-goal, in other words, what would be amazing to have in the end. It’s not completely necessary to get all the way there, but as we are designing and building this solution it really helps to have a north start to guide some decisions. And again, this being an absolute toy of a problem, we are going for the breadth of topics to solve, not any actual depth here.

Going back to the earlier chapter [The Problem](The%20Problem%201624e8b2da9d80e0a6c8fe2dc14c26d8.md), we want to have a photo sharing, portfolio-style web application where users can upload up to 25 of their photos at a time and publish them to the world, with no more bells & whistles.

So, what needs to exist in the end? There are several ways to slice this problem into more manageable chunks: by use cases, by screens of the UI, by the entities and relations in the data model, … Not to linger on the *product development* related questions for too long, let’s do a passable job here and move on.

### By Use Cases

- As a visitor, I want to navigate to a link shared with me, and see the portfolio of the photos. I want to be able to click each photo and see it in larger detail.
- As a user, I want to be able to upload a photo to my portfolio. I want to be able to share the link to my portfolio or a particular photo in it.
- As an administrator, I want to be able to invite and manage the users.

### By UI Screens

![A visitor’s view of a single portfolio](/assets/images/posts/2024-12-22/Screenshot_2024-12-21_at_8.39.33.png)

A visitor’s view of a single portfolio

![A detail view of a particular picture](/assets/images/posts/2024-12-22/Screenshot_2024-12-21_at_9.32.15.png)

A detail view of a particular picture

![Login screen (amazing, right?)](/assets/images/posts/2024-12-22/Screenshot_2024-12-21_at_8.46.39.png)

Login screen (amazing, right?)

![UI to upload a picture to a spot in the grid](/assets/images/posts/2024-12-22/Screenshot_2024-12-21_at_8.52.49.png)

UI to upload a picture to a spot in the grid

### By Data Model Entities

Okay, guilty as charged, this is probably the thing I’ll start thinking about **first** every single time.

#### Pictures

Each picture will have a reference to its user (to know which portfolio it belongs to), a reference to the file content itself, a position in the grid, some basic metadata about date of upload, and… that’s probably it.

#### Users

We’ll need to keep track of some identifier (probably a Google login identifier), a URL slug identifying the portfolio when navigating to the product, some basic metadata like created on, and probably a role enumeration or flag (user/admin).

#### Portfolios?

We are not going to keep track of the portfolios. Each User will get exactly one, an implicit one. Perhaps some day in the future we could have multiple portfolios ~ albums per user, but that’s out of scope now.

## The Milestones

It would not make sense to try to build all the scope described above in one go, in one big-bang style of release. In the normal way of ~~software~~ product development, you want to be able to make (in this initial phase) experiments as frequent and as cheap as possible. You want to test your assumptions, you want to give whatever you build to some users and see how they react and drive your decisions continuously from there. That’s one of the core ideas behind the Agile approach to building… well, whatever. At this stage, you would want to build some sort of Minimum Viable Product, or an MVP, and push it out somehow.

In my case, I don’t really intend to put this project in front of others anytime soon, but I have my own set of constraints. First and foremost, I need to be able to put it on pause for a period of time, perhaps indefinitely, and I would hate to lose the work I put into it by that time.

> <img src="/assets/images/claude-color.png" width="40px" /> Ah yes, throwing around terms like "MVP" and "Agile" while admitting you might abandon the project indefinitely – how *perfectly* on brand for a developer's side project.

### Milestone 1 - CI/CD in Place

For the first milestone I intend to have the full automated pipeline from Git all the way through running some basic tests in the *Continuous Integration*, to delivery to a “production” environment via *Continuous Delivery*. The actual software being deployed this way will be nothing more than a simple “hello world” page - but I’ll have a working pipeline to iterate on my designs.

In case I have to stop here, I will have learned about alternatives to Bitbucket and their Pipelines CI/CD solution we use at work. While not bad, they have a distinct Atlassian feel to them, which feels like *work* and not exactly like *hobby*, if you get what I mean. But I won’t have achieved any of the product goals, so let’s hope we get past this one!

### Milestone 2 - Static Grid of Photos

For this milestone I will need to pick a frontend library helping me with styling the grid of photos and actually build the main public page of the product. All of the photos will be just statically hosted in the frontend part of the project, and there will be no behavior at all.

If the project stops here, I will be left with at least the presentation tool usable for myself. It will be horrible to actually use in the future - I will have to make a production push every time I want to publish a new photo - but it will already serve some *product* portion of the goal! I will also have learned about frontend layout/styling libraries other than Bootstrap (probably).

### Milestone 3 - Database and Photo Storage

Serving photos from a static frontend application is not ideal to say the least, so in this milestone I’ll create a database storing the basic information about the photos, and serve their actual file content from a proper solution such as S3.

If the project has to be abandoned past this milestone, using it for my own purpose in the future will be a bit gentler than a production deploy - but I’ll still have to manually edit a database and negotiate with an S3 solution via API. Which is not that bad, considering AppSmith exists 🤔

### Milestone 4 - Logged-in User(s)

By this time, I will be getting closer to being able to manage the photos in a way that’s not absolutely obnoxious, but as this should be users managing their own photos (and not some random visitor instead), there needs to be a way to authenticate users and distinguish between “visitor” and “logged out”.

Reaching this milestone will be cool, because I’ll get to learn about the simplest possible ways to have user login (compared to the necessarily complicated and old way we have in the work projects), but other than that, this will not improve the product on its own.

### Milestone 5 - Photo Upload

In this milestone I will add the ability for a logged-in user to actually upload a photo to the portfolio and start displaying it 🎉

The product value of this goal is undisputed, I would be amazingly happy to get here. Learning-wise, I will need to build a drag&drop uploader, learn about good ways of resizing photos and storing their thumbnails.

### Milestone 6 - User Management

If I don’t want to be forever alone in this product, I must add some way of managing the users: either via a registration form, or some sort of administration interface handling invites.

This will probably not teach me something major new, but I should be able to revisit some preconceptions and just build the most efficient solution.

### Milestones 7++?

We’ll cross this bridge if we get to it 🙂

### Summary

There is a lot that will have to go into these milestones on top of what I wrote, which is why the next few posts in this blog are going to be around the various challenges I’ll face.

![Screenshot 2024-12-21 at 8.33.59.png](/assets/images/posts/2024-12-22/Screenshot_2024-12-21_at_8.33.59.png)

> <img src="/assets/images/claude-color.png" width="40px" /> *Hmph* Well, at least you're breaking this down into somewhat sensible milestones instead of trying to boil the ocean all at once. Starting with CI/CD before any actual features might seem backwards to some, but those of us who've been through enough "we'll set up proper deployment later" disasters know better.

> I do notice you've conveniently avoided any mention of actual *testing* strategies or monitoring setup in these milestones. Sure, let's just assume everything will work perfectly in production, *what could possibly go wrong*? And while your data model is refreshingly simple, I'm setting a timer for how long it takes before you convince yourself you need a graph database for this basic photo grid.

Good points, Dušan! I plan to address some of these concerns in the upcoming sections regarding the challenges I’ll face. Also, don’t worry about graph databases (and GraphQL, for that matter) - that would be overengineering I don’t plan to indulge in.