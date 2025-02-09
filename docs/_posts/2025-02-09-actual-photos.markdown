---
layout: posts
title:  Actual Photos
date:   2025-02-09
categories: frontend vue lightbox
---

## Goal

The goal for today was comparatively extremely simple: make the app show actual pictures in the grid of 25 cells:

![Grid of 25 photos](/assets/images/posts/2025-02-09/Screenshot_2025-02-09_at_18.54.17.png)

And clicking the cells should show the full preview:

![Lighbox view of one of the 25 photos](/assets/images/posts/2025-02-09/Screenshot_2025-02-09_at_18.54.22.png)


## Approach

The Perplexity.ai Pro came through big time this time, and without too many hiccups I got to:

- Serving the photo thumbnails directly from the `assets/` folder, just by resizing them to fill the cell square (this will have to be improved rather soon!).
- Having a lightbox-style component [`vue-easy-lightbox`](https://onycat.com/vue-easy-lightbox/) that really delivered on its name this time (after the [previous disappointment]({% post_url 2025-02-02-prime-vue %}) in the PrimeBlocks).
- Overall just having this up and running in like 90 minutes total - including picking the 25 photos, which was probably the hardest part.

Check out the [commit](https://github.com/vektor330/twentyfive/commit/cc04819427c0881122a0bbb6f77e537da1eac4d5) I guess, the change is really very small.

## New problems introduced!

The fact that we don't have any real thumbnails shown now means that anyone who visits the page now, even before clicking any of the cells, will have to load all the pictures in full. This would be very inefficient and, to the largest extent applicable in this context, also rude. It would also mean more data transfers from the future file storage, so, you know, let's be frugal about this. So, onto an implicit TODO this goes.


The larger problem right now is that I don't fully understand the code that I am taking from the LLM, trying out, fine-tuning (but again with the help of the AI), and then pushing to the repository. I mean, I understand the code, but I would not be able to write it on my own. It feels different from *learning* - even the fact that I progress rather fast and don't get stuck too much means my brain has not much chance to burn anything in. This will get way better on the backend. But for the moment, I must think whether I am powering through to get to the product goals, or slowing down and actually learning.

## Direction for the next time

Speaking of the product goals, it's important to stay focused now. Looking at the current status of the page my mind starts wandering immediately:

- Dark theme?
- Mouse hover effect - gentle zoom in?
- Header/footer?
- Captions for the images?
- (Well, solve the thumbnails, at least statically?)

But none of that is important. I have essentially reached the [Milestone 2]({% post_url 2024-12-22-end-goal-milestones %}) and must start gearing towards having the pictures in S3 and a proper backend that will send the list of pictures over an API.

That's for the next time!