import { Component, OnInit } from '@angular/core';
import { POSTS } from 'src/app/mock-posts';
import { Post } from 'src/app/models/Post';

@Component({
  selector: 'app-post-feed',
  templateUrl: './post-feed.component.html',
  styleUrls: ['./post-feed.component.css']
})
export class PostFeedComponent implements OnInit {
  posts: Post[] = POSTS;

  constructor() { }

  ngOnInit(): void {
  }

}
