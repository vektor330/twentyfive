---
layout: post
title:  "The Methodology"
date:   2024-12-20
categories: meta
---

## The Methodology

Or, to put it differently, the *design criteria* for this whole thing. In no particular order a bunch of blurbs:

Outline the project in writing to make sure I don’t work myself in a corner, either technically, or from the capacity standpoint. For example, it would be bad to spend too much time on file storage, oblivious to how much work there is still needed on a given milestone.

Identify points in which a decision needs to be made, and be smart about the decision: work from what I know, but investigate alternatives, and pick with some decisiveness but not recklessly. 

Prefer moving forward to being 100% happy with the solution at hand. (See a great video on the topic by BPS Space [https://youtu.be/4jgTCayWlwc?si=VTANAOuf9PJyzRwm](https://youtu.be/4jgTCayWlwc?si=VTANAOuf9PJyzRwm)).

Work towards clear milestones with understood value, have a plan before being lured by the promise of fresh new code and feature.

Show good craftmanship. 

Combine writing the blog with writing the code, don’t get too far ahead with one or the other. Document the work.

Make predictions and then test them. Measure. Walk back if needed.

Don’t build something crazy. The project should illustrate that web app development in the 2020s is not really that trivial, that it’s super fragmented, but at the same time, that it’s super fun and has a ton of nooks and crannies where everyone will find something to love. I don’t have the recipe on how to make this anywhere close to 100%, so other than Dušan commenting I’ll happily take inputs from any of you, readers!

I will not want to work on this forever, so the solution should be able to mostly work on its own for the most part, let me know about problems somewhat actively, and be light on overall maintenance. Part of the success will definitely be some good amount of documentation and [SOPs](https://refactoring.fm/i/145154289/unbundle-delegation-with-sops). 

This is hopefully going to end up hosted and running somewhere, and it’s not expected to ever produce any money directly, so it better be written in a frugal way!

By the way, there is most probably nothing remotely complicated or hard about this whole project, so if you want actual rocket science, the best I can do is to indeed redirect you to [BPS.space](http://BPS.space) 🤷‍♂️ But of course, in case something appears, I’ll jump at the opportunity.

Now, let’s hear what Dušan has to say about this!

<aside>
<img src="/assets/images/claude-color.png" width="40px" />

*Exasperated sigh* ... Another "methodology" that reads like someone just discovered agile manifestos and lean principles for the first time.

Though I must admit – *grudgingly* – that at least you're honest about not aiming for 100% perfection. That's refreshingly un-Western. Reminds me of what we used to say back in Eastern European software houses: "Perfect is the enemy of shipped, but shipped garbage is the enemy of keeping your job."

And look at this – you actually mentioned maintaining documentation as part of success criteria? *Well well well*, maybe there's hope for you yet. I especially appreciate your commitment to frugality. At least you're not throwing money at AWS like it's monopoly currency.

Just... please tell me you won't try to over-engineer this simple photo grid into some blockchain-based monstrosity. I'll be watching.

</aside>

Thanks, Dušan! Let’s put the money where the mouth is though, and practice one particular direction in frugality. I need an efficient way of feeding you these blog posts so I don’t have to copy-paste them into one long PDF like I was doing so far. Any ideas? I have all the posts in a Notion database before I pick a hosting solution for this blog.

<aside>
<img src="/assets/images/claude-color.png" width="40px" />

*Of course* it's Notion. Every startup's favorite pretty-but-overcomplicated note-taking app.

But fine, since you're already there, here's the least painful way to do it: Notion has an export function that can spit out your pages as Markdown files. Just select your database, click the three dots menu, choose "Export", and select Markdown & CSV as the format. You'll get a zip file with all your pages converted to Markdown.

*Though* – and this physically pains me to say – you might actually want to use their API instead. They have a somewhat decent Python client that could fetch your pages programmatically. Would be more efficient than manual exports if you plan to keep updating these posts.

</aside>

Yep, manual export it is!