---
layout: post
title:  Blogging Platform Selection
date:   2025-01-03
categories: meta blogging
---

## Challenge

After having written the past 10 articles in Notion as a proof that I won’t get stuck before I start, it’s now time to go and find a platform to host this blog from now on.

## Success criteria

All of the articles are hosted online on a domain I’ll still have to purchase, without a loss of fidelity (pictures included), linked together, with a simple way to add more.

Ideally, the solution would be free. Optionally, the source code of the articles could be hosted in a Git repository (”docs-as-code”, in this case “blog-as-code”), just to make this project self-contained in one place as much as possible.

## Investigation

A quick googling reveals that the simple solutions would include Wordpress (no thanks, bad experience with the software), Medium (a bit pretentious), Tumblr (🤔), LinkedIn (over my dead body), Blogger (I used it in the past, is that still a thing?) and a few “deploy it yourself” content management systems. That’s… boring, to say the least.

ChatGPT recommends a few more options based on Git and Markdown: GitHub Pages or Netlify for hosting, and a Static Site Generator (SSG) for Markdown to HTML conversion. Let’s look into these!

### Github Pages

Feels very much like an early lock-in to GitHub overall, which was always on the table. The recommended SSG aka Markdown-to-HTML converter is Jekyll, mentioned immediately on [https://pages.github.com](https://pages.github.com/). It should be possible to use own domain, and the whole thing should fit into the otherwise free GitHub account.

This is a clear winner so far then. But let’s not call it just yet?

### Netlify

Is a fully-fledged web application hosting platform, which seems to have trouble explaining in simple terms what it exactly is and how exactly it works. Guides sorted by release date and not by some conceptual order are… not useful? Took me three minutes to find this [https://docs.netlify.com/platform/what-is-netlify/](https://docs.netlify.com/platform/what-is-netlify/) which basically states that the core is the ability to host “any JavaScript framework”.

At the same time, this description [https://docs.netlify.com/platform/who-is-netlify-for/#for-documentation-teams](https://docs.netlify.com/platform/who-is-netlify-for/#for-documentation-teams) sounds like exactly what I need to achieve. And, there is a free tier.

### Vercel

Seems super similar to Netlify on the first look, with the same concept “composable app” being thrown around. And the same concept of having all the possible JavaScript frameworks supported.

But I am going in another direction, I really want to have a non-JavaScript backend server here. And I don’t want to be stuck on a service that expects me to deploy FooBar.js 0.1 “any minute now”.

## Decision

At the end of the day, unless something serious happens while investigating the alternative Git hosting services, having all under one roof of GitHub is going to be simpler to set up and maintain. Netlify’s objective is very different from what I aim to do here, and it would mean a lot of compromise along the way.

<aside>
<img src="/assets/images/claude-color.png" width="40px" />

*Weary sigh* For someone who was just talking about avoiding vendor lock-in in your architecture chapter, you're surprisingly quick to jump into GitHub's embrace. Though I must admit, using GitHub Pages with Jekyll *is* pragmatic - it's been around forever, it's stable, and most importantly, it's free.

Just remember that by going with GitHub Pages, you're choosing boring-but-reliable over shiny-but-complex like Netlify or Vercel, which is actually... *reluctantly nods* ...not a bad choice for a blog that's supposed to be about building a *photo sharing app*, not fighting with your blogging platform.

</aside>