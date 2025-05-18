---
layout: posts
title:  Upload works!
date:   2025-05-18
categories: backend frontend
---

I re-did the attempts from the [last time]({% post_url 2025-05-09-what-now %}) in a less ambitious way, leading paradoxically to a *working* upload mechanism that allows me to replace the images in the single gallery that exists so far.

[The change](https://github.com/vektor330/twentyfive/commit/e6285bc4c83dd17bc04cf2b70640ac05e2f1dd8c) ended up being rather huge, because apart from the upload UI, API endpoint, and backend logic I had to make sure only users declared "admin" in the Auth0 can perform this upload - lest someone registers (no limits there yet!) and uploads `nevergonnagiveyouup.bmp`.

But let's take this from the start.

## Frontend

This was a lot of vibe-coding again, but this time I went in with the smallest assignment possible: just a trivial form (no modal dialog, no PrimeVue, ...) below the gallery, and just talk to an API that was ready beforehand this time. And it worked!

## Backend

The [`/upload` endpoint](https://github.com/vektor330/twentyfive/commit/e6285bc4c83dd17bc04cf2b70640ac05e2f1dd8c#diff-c84c0426dca1e0999f4d1c81ca6b0865bb8f81d7b8f92416db48e81367a718e0) and the surrounding business logic was ridiculously smooth sailing compared to the pain of the frontend vibe crunching, the only interesting thing was to figure out a way to [fetch the user permissions from the JWT token](https://github.com/vektor330/twentyfive/commit/e6285bc4c83dd17bc04cf2b70640ac05e2f1dd8c#diff-4feac5ea29c8a62e9648a81f9c7e267dabb697904b05ce74d8313bfb42a25ed5) to determine if upload should be allowed.

For reasons I must investigate further this is not something straightforward to do on the frontend (gist being "send the token to the backend and let it give you back frontend permissions" or such), so let me consult Dusan:

> <img src="/assets/images/claude-color.png" width="40px" /> So you're having Auth0 issues with your little photo grid project. Let me guess - everything works perfectly on the backend but the frontend is a complete mystery? *Classic*.

> But I can explain what's likely happening: Auth0 uses different token types for different purposes, and you're probably confusing them.

> When your backend API receives an access token, it contains claims about permissions and roles because it's *designed* for authorization. That's its whole purpose - telling your API what the user is allowed to do.

> Meanwhile, on the frontend, you're probably using an ID token, which is primarily for *authentication* - proving who the user is, not what they can do. By default, Auth0 doesn't include role or permission claims in ID tokens.

> There are three ways to fix this:

1. Use the Auth0 Management API from your frontend to fetch the user's roles/permissions (overkill and terrible security practice)

2. Create a simple endpoint on your backend that returns the user's roles/permissions (the sensible approach)

3. Configure Auth0 to include the roles and permissions in the ID token via custom claims (works but feels like a hack)

> But honestly, option 2 is cleaner. Create a `/me` endpoint on your backend that returns the user info including roles.

> Of course, all of this assumes you've actually set up Auth0 properly in the first place. Given that your last blog post mentioned you couldn't even get a modal dialog to open, I'm skeptical.


*(side-note: Dusan can now be fed the past blog posts for context directly from Github 🎉)*

### Picking a new object storage provider

Remember that [so far]({% post_url 2025-02-22-s3 %}) we had the sample pictures for the only gallery uploaded to [Vercel blobs](https://vercel.com/docs/vercel-blob). The API for it however did not feel documented well enough: just the Javascript framework SDKs, not even a straightforward HTTP API documentation I could adapt to Java via some light LLM work. Their overall communication did not inspire much confidence either: they recently offloaded their Postgres to Neon and their KV store to some 3rd party, so let's yeet before we are yeeted.

After a short investigation into the possible providers I settled on Scaleway (🇪🇺 being a positive). Had to fight their IAM and policies for some minutes but got everything running in the end.

Similar choice had to happen on the level of the Java SDK to talk to this S3-compatible provider, and here I went for [MinIO](https://min.io) which feels like an invitation to also use it as an S3 provider when developing locally.

## [Sentry](https://sentry.io)

This is a must: the barely-minimal [o11y](https://www.logicmonitor.com/blog/what-is-observability) and we love it at work and I love it with all my heart, so here it is. The setup was straightforward, nuff said.

## Vercel builds

You remember [last time I mentioned]({% post_url 2025-05-09-what-now %}) that I set both Railway and Vercel to only perform a build if their respective folder (`backend/` or `frontend/`) changes? Well, guess which of the two services got the memo and which did not?

Luckily, it was simple enough to navigate to the list of Vercel builds, find the one that got skipped in the early stages of processing, and re-run it manually. But, it's tedious, so Vercel is back to building every time there is a push to the repo 🤷‍♂️

## Next steps

*You mean, apart from finding the time and energy to work on this?* Well, a lot of polishing should happen before I start some new major functionality:

- [`frontend/.env`](https://github.com/vektor330/twentyfive/blob/main/frontend/.env) seems to contain stuff that flew under the LLM's radar so far, but it feels like it should be removed from the repo going forward.
- Any new frontend changes would benefit a ton from having E2E tests to make sure my vibe coding is not vibe breaking already working stuff. Backend does not get a free pass, too: compared to the way we have things set up at work, it should be way more prepared to support integration tests I lack experience with so far.
- There is a lot of just plain old moving code around, tidying it up and so on - more on the front-end. See, tests would help 🤔
- The UX of uploading the photo is medieval, but that's more like a new feature, so let's only do that later.

Having said that, the above is in *theory* chunked in a way that should support being able to just spend 30-60 minutes on it every week day. Last week, it was really not the case, so let's see about the next one.
