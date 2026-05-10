import { Component, OnInit, PLATFORM_ID, inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule],
  template: `
    <h2>Your Protected Tasks</h2>
    <ul>
      <li *ngFor="let task of tasks">{{ task }}</li>
    </ul>
  `
})
export class TaskListComponent implements OnInit {
  tasks: string[] = [];
  private platformId = inject(PLATFORM_ID); // Inject Platform ID
  constructor(private http: HttpClient) { }

  ngOnInit() {
    // Note: We don't manually add headers here! 
    // The Interceptor we built handles that automatically.
    if (isPlatformBrowser(this.platformId)) {
      this.http.get<string[]>('http://localhost:8080/api/tasks').subscribe({
        next: (data) => this.tasks = data,
        error: (err) => console.error('Could not fetch tasks', err)
      });
    }
  }
}