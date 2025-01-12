---
layout: post
title:  Vue.js Build System
date:   2025-01-12
categories: infra ci/cd
---

Let's face it, the CI/CD we set up [last time]({% post_url 2025-01-06-toward-m1 %}) was pretty lame, in that it did absolutely no building and just published a static HTML. So the goal today is to set up a proper Vue.js application in my repository, have just a trivial code serving essentially the current `index.html` in there, but have it built properly by Vercel.

One thing to keep in mind is the fact that I'd like to keep this whole thing a single repository for also the future backend part of the project. So let's do a bit of investigation!

First things first - can I even have this? (Yes) Does it depend on the backend hosting provider? (Not much) So which one to pick? Claude recommended Railway.app and Render, so based on gut feeling looking at their landing pages I picked **Railway**. Currently being very unhappy, as it is supposed to be connected to a Git repository that is not configured for it anyhow.

In any case, it's time to make sure our repository can handle both the frontend and the backend parts of the project, and for that purpose, I am just going to have structure like this:

```
/
├── frontend/           # frontend Vue.js project
│   ├── src/
│   ├── public/
│   └── package.json
│
├── backend/            # future backend project
│
├── .gitignore
└── README.md
```

Moving the existing `index.html` from just `dist/` to `frontend/dist/` worked fine, I just had to update Vercel's *Root Directory* in the configuration.

Now comes the hard part: pick a frontend framework to use with Vue.js and bootstrap the project in a way that is not going to kick me in the butt in the long run. Why am I stressed? Because even though going with Vue.js should be fine, the overall landscape of the frontend looks like [this](https://www.youtube.com/watch?v=aWfYxg-Ypm4) nowadays.

Let's ask Dušan, shall we?

<aside>
<img src="/assets/images/claude-color.png" width="40px" />
*Sigh*... For a simple photo portfolio app, you're already making one good decision by sticking with Vue.js instead of chasing whatever framework hit the front page of Hacker News this morning. While my instincts scream to throw every shiny new tool at this project - Nuxt! Pinia! UnoCSS! - let's do something *truly* radical and start with just the basics. Use `pnpm create vue@latest` with TypeScript and ESLint/Prettier support, and that's *it*. No need to turn your `package.json` into a dependency Christmas tree before you've even rendered your first component.

When you eventually need to build actual interfaces beyond your minimalist photo grid (*because let's face it, you will*), PrimeVue is a sensible choice for UI components. Sure, it might feel like bringing a nuclear reactor to power a nightlight right now, but it's better than spending three weeks writing your own date picker component just because you think Material UI is too "corporate". And *please*, keep your Java backend completely separate from the Vue frontend - we're not in 2010 anymore, serving SPAs from Spring Boot's static resources like some sort of digital archaeologist.

Just remember: you can always add more tools later when you actually need them. Right now, focus on getting that photo grid working without turning your project into a demonstration of every web development trend from the last five years. *Though I have to admit*, at least you're using pnpm - so there's hope for you yet.
</aside>

Okay so I did this: 

```
pnpm create vue@latest frontend
```

and then had to change all the default-generated Vue.js project files to the beautiful, Dušan-crafted `index.html` goodness. Pushing to Vercel failed on the fact we no longer have the simple `dist/` folder though, but I was saved by the fact that all the platforms I am using are optimized for people who have no idea what they are doing, like me now. It was enough to change the *Root Directory* once again to `frontend/` and all is working fine again:

![A trivial Hello World Vue.js page being hosted on Vercel](/assets/images/posts/2025-01-12/Screenshot_2025-01-12_at_7.26.54.png)

Which marks the end of another session - we now have a Vue.js frontend project that must contain like 10+ serious problems. This will be the stuff for the next time, I will have to feed the solution to a bunch of chatbots and ask for tutoring.