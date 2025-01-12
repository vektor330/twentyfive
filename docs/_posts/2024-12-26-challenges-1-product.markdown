---
layout: posts
title:  "Challenges: 1 - Product"
date:   2024-12-26
categories: meta challenges
---

## Challenges

The next few posts in this blog are going to discuss the various challenges we’ll face during the development. It is a sort of a mindmap or a TODO list for us going forward. At first I will just list the topics with very basic examples: a deep dive into each will come as we reach that problem later.

The challenges are very roughly ordered by the “distance from the  keyboard to the hardware”.

## Product challenges

### Design

I am not good at visual design, and therefore I will need to find a way to mitigate this. Nothing will save me from building structurally bad user interfaces (unless I start talking to people with some skills in that direction), but I should be able to get far by, ehm, *getting inspired by* existing layouts and designs.

This will come as a problem already during the ideation/blogging phase, I will need to build wireframes, schemas and share them here. My plan is to use [Freeform.app](https://support.apple.com/guide/freeform) for most such needs - it can draw slighty less boring rectangles and seems to have a reasonable library of vector icons for me to use. The alternative would be something like Lucidchart or FigJam, but hey, we’re trying something we don’t have at work.

![Screenshot 2024-12-21 at 9.59.23.png](/assets/images/posts/2024-12-26/Screenshot_2024-12-21_at_9.59.23.png)

I must resolve at least two major topics soon:

- Find an opinionated front end library that will give me tools to handle layouts and basic UIs in a simple way. Buttons, grids and similar must be not something I spend time on. Bootstrap comes to mind first, but I’ll investigate more. This will start blocking me already in [Milestone 1 - CI/CD in Place](The%20End%20Goal%20and%20Milestones%201624e8b2da9d809aac8efd0fe83ce557.md).
- Figure out a logo and icon for the project. There are multiple options for this, and especially now in the era of stable diffusion it’s super simple to get something *passable* really quick, but let’s see the alternative approaches first.

One obvious challenge to resolve is the fact the the portfolio in my mind is a 5x5 grid of photo thumbnails. Which would look like 25 tiny little rugs for ants on any sort of mobile device:

![Screenshot 2024-12-21 at 10.10.27.png](/assets/images/posts/2024-12-26/Screenshot_2024-12-21_at_10.10.27.png)

Source of the picture: http://pngwing.com/

### User experience

Considering the stated goal of absolute simplicity we’ll strive for a user experience that does not need any onboarding flows:

- Log in as you would to any other web app.
- Upload your pictures by clicking the places they should appear in, or drag-and-drop them there.

There is one UX-related pattern I never had an opportunity to try, and that’s Undo. Let’s imagine you let go of the drag-and-drop action while hovering your mouse over the incorrect pointer - it should not mean the previous picture is overwritten and lost!

### As a thing available to the public

…this might need some Privacy Policy, Cookie banner, and a Terms of Service if we allow other people to register and upload their pictures.

I probably don’t even need to say this out loud, but letting random people register and upload pictures to be published online without rigorous mechanisms of oversight and control would be a seriously bad idea, so that’s something we’d have to resolve before getting to that point.

Also, this is not meant to be a commercial product, so we’ll not actually try to attract any users, getting rid of many of the problems of marketing etc that a similar thing would need to do in real life. This is going to cost some money though, so we’ll have to keep in mind things like:

- Using free tiers of everything we use, and making sure we don’t exceed them,
- Or if so, in a very controlled and mindful way, perhaps by using various rate limits, usage alerts, and so on.

### As a presentation platform

Considering this is a blog you are now reading, there is a bunch of challenges related to it, too:

- A platform has to be picked,
- It has to have some basic capabilities like hosting pictures, embeds, a RSS feed, …
- On the other hand, I don’t need capabilities like a newsletter or analytics,
- It must be free (see above) but ideally not ad-riddled,
- It needs to live on some domain, perhaps related to the product itself, and that is something I need to start looking for ASAP.

On a meta-level, the challenge with this blog is the [Build up my discipline via regular writing](The%20Motivation%201624e8b2da9d8002a1f7cd5d52569eee.md). Let’s see how that goes 🤞

Any parting comments, Dušan?

> <img src="/assets/images/claude-color.png" width="40px" />

> Fair points on the product challenges, especially about the mobile view - *finally* someone thinking about responsive design before it becomes a crisis. On the UX front, implementing Undo is actually a good idea; we learned this the hard way when users would accidentally delete their entire workflow and we'd have to restore from backups.

> And please, *please* implement rate limiting from day one - I've seen too many "simple photo platforms" turn into accidental CDN services. Though I suppose that would be an interesting problem to have...

Yeah, will do. Let’s now see about the development challenges.