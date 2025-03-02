---
layout: posts
title:  The Valley of Bore
date:   2025-03-02
categories: backend postgres flyway spring
---

## Rant

This must be the most boring part of the project so far, and I either find some exciting stuff or at least interesting problems, or I'll have to speedrun this whole thing somehow.

## Database, migrations, and Spring Data

Today's goal was to take the list of Pictures from a database, instead of from being hardcoded in a controller. Well, taking exactly the pieces I either know by heart, or that I *mostly* know how they work, I got this running in approximately one hour, including the largest problem - figuring out how to connect IntelliJ's database tool to the Neon database.

Let's start from the top then. For database I picked [Neon](https://neon.tech) - I've been using it for some time now for my local environment at work. The free tier gives me a database that's not breaking any speed records, but has some super cool capabilities like auto-scaling all the way to zero, branching and did I mention it's free? So far so boring, one opportunity here is to set up local branching to [automatically create database branches](https://neon.tech/docs/introduction/branching).

Next up: migrations. What feels like milion years ago, I was working on the super old versions of the work app by comparing my database structure to the production structure, and that... was the script to execute together with the version release. Needless to say, I am vaccinated since a long time ago, and today was an opportunity to try out a database that needs the very first migration to even get its second table (the first being `flyway_schema_history` of course). This is probably a chance to try out some better ways of seeding databases with non-trivial data - but at the moment, I literally have just the two tables. In any case, the first migration now has all the 25 URLs that are lucky to no longer be hardcoded in Java.

Finally, talking to this database from Spring. Pure Spring Data is super low-code, as in, you barely have to write any code to make it work, once you get past the initial setup hurdles. Good to see Hikari Connection Pooling, it's not like we have it at work 🤷‍♂️ Having converted the `PictureDto` to a proper entity now, the opportunity here is around reducing boilerplate via Lombok, or just sitting down and figuring out my take on the philosophy of entities x DTOs.

Oh, and instead of an <del>`int`</del> `long` autoincrement table ID column, I finally am playing with a GUID-based approach - after *years* of just talking about them in technical interviews! Time to put the money where the mouth is, I guess.

## Takeaway

Apart from my ranting, this is actually the part of development that is ridiculously simple, as long as you somehow know what you are doing. We have it way too complicated at work due to technical debt and overall inertia (before you ask, yes, most of it is on me) - MyBatis with XML repositories, Postgres cluster with a read-only node, and fighting the occasional timeout, pool size and deadlock is not something I expect to ever reach in TwentyFive.

## Next time

Two basic options: 

1. Either I play it safe a bit longer and e.g. set up the ability to have multiple galleries and pick via the URL path (doing some migrations, Spring, and - gasp - foreign keys),
2. Or I jump straight to user authentication to try something borderline *exciting*?

...probably the former, but a few free days are coming up, so I should stop stalling and take a much larger bite!