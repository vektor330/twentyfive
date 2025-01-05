---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: home
---

{% assign intro_post = site.posts | where: "categories", "intro" | first %}
{% if intro_post %}
## {{ intro_post.title }}
{{ intro_post.content | strip_html | truncatewords: 50 }}
[Read more...]({{ intro_post.url }})
{% else %}
_No intro post found._
{% endif %}